package com.hwj.demo.proxy;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface Subject {
    /**
     * 你好
     *
     * @param name
     * @return
     */
    public String SayHello(String name);

    /**
     * 再见
     *
     * @return
     */
    public String SayGoodBye(String name);
}
