package com.hwj.demo.service;

import com.hwj.demo.entity.Log;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：操作日志服务接口
 */
public interface LogService {

    public void insertLog(Log log);
    public void updateLog(Log log);
}
