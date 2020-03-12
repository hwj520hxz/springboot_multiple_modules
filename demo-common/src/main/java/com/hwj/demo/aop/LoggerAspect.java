package com.hwj.demo.aop;

import com.hwj.demo.annotations.LoggerOperator;
import com.hwj.demo.entity.Logger;
import com.hwj.demo.service.LoggerService;
import com.hwj.demo.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：日志切面
 */

@Aspect
@Component
public class LoggerAspect {
    @Autowired
    private LoggerService loggerService;

    // 定义切点,那些持有该注解的方法将得到增强
    @Pointcut("@annotation(com.hwj.demo.annotations.LoggerOperator)")
    public void controllerAspect(){
    }

    //前置通知，就是在有注解的接口执行前执行该方法
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            int port = request.getServerPort();
            String ip = IpUtil.getIpAdress(request);
            String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
            String desc = getControllerMethodDescription(joinPoint);
            Logger logger = new Logger();
            logger.setIp(ip);
            logger.setOperatorId(null);
            logger.setMethod(method);
            logger.setDescription(desc);
            logger.setPort(port);
            loggerService.insertLog(logger);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String desc = "";
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                Class[] classes = method.getParameterTypes();
                if(classes.length == args.length){
                    desc = method.getAnnotation(LoggerOperator.class).description();
                    break;
                }
            }
        }
        return desc;
    }
}
