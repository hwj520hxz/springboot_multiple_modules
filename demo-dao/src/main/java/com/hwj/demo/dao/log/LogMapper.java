package com.hwj.demo.dao.log;

import com.hwj.demo.entity.Log;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：日志管理mapper
 */
public interface LogMapper {

    /**
     * 功能说明:新增日志
     **/
    void insertLog(Log log);

    /**
     * 功能说明:更新日志
     **/
    void updateLog(Log log);

}
