package com.hwj.demo.aop;

import com.alibaba.druid.support.json.JSONUtils;
import com.hwj.demo.annotations.SystemControllerLog;
import com.hwj.demo.annotations.SystemServiceLog;
import com.hwj.demo.entity.Log;
import com.hwj.demo.service.LogService;
import com.hwj.demo.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@Aspect
@Component
public class SystemLogAspect {
    private static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");
    private static final ThreadLocal<Log> logThreadLocal =  new NamedThreadLocal<Log>("ThreadLocal log");

    @Autowired(required=false)
    HttpServletRequest request;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    LogService logService;

    @Pointcut("@annotation(com.hwj.demo.annotations.SystemServiceLog)")
    public void serviceAspect(){}
    @Pointcut("@annotation(com.hwj.demo.annotations.SystemControllerLog)")
    public void controllerAspect(){}

    @Before("controllerAspect()")
    public void doBefore(){
        logger.info("进入日志切面前置通知!!");
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);  //线程绑定变量（该数据只有当前请求的线程可见）
        if (logger.isDebugEnabled()){  //这里日志级别为debug
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(beginTime), request.getRequestURI());
        }
    }
    /**
     * 功能说明:
     * 通过线程池来执行日志保存,管理这些线程并避免频繁地去创建和销毁它们，对于系统的性能和稳定性有很好的提升
     **/
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("进入日志切面后置通知!!");
        String title="";
        String type="info";
        String remoteAddr=request.getRemoteAddr();
        String requestUri=request.getRequestURI();
        String method=request.getMethod();
        Map<String,String[]> params=request.getParameterMap();
        try {
            title=getControllerMethodDescription2(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long beginTime = beginTimeThreadLocal.get().getTime();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();
        if (logger.isDebugEnabled()){
            logger.debug("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                    request.getRequestURI(),
                    endTime - beginTime,
                    Runtime.getRuntime().maxMemory()/1024/1024,
                    Runtime.getRuntime().totalMemory()/1024/1024,
                    Runtime.getRuntime().freeMemory()/1024/1024,
                    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
        }
        logger.info("设置日志信息存储到表中!!");
        Log log=new Log();
        log.setLogId(UuidUtil.createUUID());
        log.setTitle(title);
        log.setType(type);
        log.setRemoteAddr(remoteAddr);
        log.setRequestUri(requestUri);
        log.setMethod(method);
        log.setMapToParams(params);
        log.setException("无异常!");
        Date operateDate=beginTimeThreadLocal.get();
        log.setOperateDate(operateDate);
        //1.直接执行保存操作
        //this.logService.insertLog(log);
        //2.优化：异步保存日志
        //new Thread(new SaveLogThread(log,logService)).start();
        //3.再优化：通过线程池来保存日志
        threadPoolTaskExecutor.execute(new SaveLogThread(log, logService));
        logThreadLocal.set(log);
    }

    /**
     * 功能说明:使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     **/
    @AfterReturning(returning = "object", pointcut = "controllerAspect()")
    public void doAfterReturning(Object object) throws Throwable{
        //处理完毕，返回内容
        logger.info("=========返回参数日志===========");
        //logger.info("返回接口响应参数:" + JSONUtils.toJSONString(object));
        Log log = logThreadLocal.get();
        if(log != null){
            log.setMapToParams(request.getParameterMap());
            logger.info("============更新日志参数=============");
            new UpdateLogThread(log,logService).start();
        }
    }
    /**
     * 功能说明:使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
     **/
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e) throws Throwable{
        logger.info("进入日志切面异常通知!");
        logger.info("异常信息:" + e.getMessage());
        Log log = logThreadLocal.get();
        if(log != null){
            log.setType("error");
            log.setException(e.toString());
            new UpdateLogThread(log,logService).start();
        }

    }

    /**
     * 功能说明:取注解中对方法的描述信息 用于service层注解
     **/
    public static String getServiceMthodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemServiceLog serviceLog = method.getAnnotation(SystemServiceLog.class);
        String description = serviceLog.description();
        return description;
    }

    /**
     * 功能说明:取注解中对方法的描述信息 用于Controller层注解
     **/
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method.getAnnotation(SystemControllerLog.class);
        String description = controllerLog.description();
        return description;
    }

    /**
      * 使用异步保存日志
      * 可以继承其他的类，在这种方式下，可以多个线程共享同一个目标对象，所以非常适合多个相同线程来处理同一份资源的情况
      */
    private static class SaveLogThread implements Runnable {
        private Log log;
        private LogService logService;
        public SaveLogThread(Log log, LogService logService) {
            this.log = log;
            this.logService = logService;
        }
        @Override
        public void run() {
            logService.insertLog(log);
        }
    }

    /**
      * 使用线程更新日志
      * 继承Thread，如果需要访问当前线程，无需使用Thread.currentThread()方法，直接使用this，即可获得当前线程，不能再继承其他的父类
      */
    private static class UpdateLogThread extends Thread {
        private Log log;
        private LogService logService;
        public UpdateLogThread(Log log, LogService logService) {
            super(UpdateLogThread.class.getSimpleName());
            this.log = log;
            this.logService = logService;
        }
        @Override
        public void run() {
            this.logService.updateLog(log);
        }
    }






}
