package com.hwj.demo.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：CGLIB动态代理会让生成的代理类继承被代理类，并在代理类中对代理方法进行强化处理
 * 但是如果被代理类被final修饰，那么它不可被继承，即不可被代理
 * 创建代理类的过程: 生成代理类的二进制字节码文件;加载二进制字节码，生成Class对象;通过反射机制获得实例构造，并创建代理类对象
 */
public class HelloMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开始使用CGLIB动态代理!!!");
        /** 这里的方法是调用不是反射 **/
        Object object = proxy.invokeSuper(obj,args);
        return object;
    }
}
