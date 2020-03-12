package com.hwj.demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
/*@Aspect*/
@Component
public class CommonAop {
    /**
     * @Description: 定义切入点，切入点为com.boss.demo.controller.UserController中的所有函数
     **/
    @Pointcut("execution(public * com.hwj.demo.web.user.UserController.*(..)))")
    public void CommonAspect(){

    }

    @Before("CommonAspect()")
    public void doBefore(){
        System.out.println("执行之前");
    }

    @After("CommonAspect()")
    public void doAfter(){
        System.out.println("执行之后");
    }

    @AfterThrowing("CommonAspect()")
    public void doAfterThrowing(){
        System.out.println("执行异常之后");
    }
}
