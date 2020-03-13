package com.hwj.demo.util;

import java.util.UUID;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class UuidUtil {
    public static String createUUID(){

        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
