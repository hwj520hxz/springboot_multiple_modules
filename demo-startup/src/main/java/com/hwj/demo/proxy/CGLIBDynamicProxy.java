package com.hwj.demo.proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class CGLIBDynamicProxy {
    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        /** 设置目标类 **/
        enhancer.setSuperclass(RealSubject.class);
        /** 设置回调函数 **/
        enhancer.setCallback(new HelloMethodInterceptor());
        /** 正式创建代理类 **/
        RealSubject proxy = (RealSubject) enhancer.create();
        /** 调用目标类方法 **/
        String hello = proxy.SayHello("XXX");
        System.out.println(hello);
        String goodBye = proxy.SayGoodBye("XXX");
        System.out.println(goodBye);
    }
}
