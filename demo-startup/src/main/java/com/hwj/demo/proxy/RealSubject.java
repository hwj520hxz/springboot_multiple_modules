package com.hwj.demo.proxy;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class RealSubject implements Subject{
    /**
     * 你好
     *
     * @param name
     * @return
     */
    public String SayHello(String name)
    {
        return "hello " + name;
    }

    /**
     * 再见
     *
     * @return
     */
    public String SayGoodBye(String name)
    {
        return "good bye " + name;
    }
}
