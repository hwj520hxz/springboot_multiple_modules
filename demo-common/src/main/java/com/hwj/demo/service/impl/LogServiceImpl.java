package com.hwj.demo.service.impl;

import com.hwj.demo.dao.log.LogMapper;
import com.hwj.demo.entity.Log;
import com.hwj.demo.exception.BusinessException;
import com.hwj.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：日志管理服务实现类
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void insertLog(Log log) {
        try {
            logMapper.insertLog(log);
        }catch (Exception e){
            e.printStackTrace();
            String errMsg = MessageFormat.format("日志编号[{0}]新增出现异常,异常信息为[{1}]",log.getLogId(),e.getMessage());
            throw new BusinessException(errMsg);
        }
    }

    @Override
    public void updateLog(Log log) {
        try {
            logMapper.updateLog(log);
        }catch (Exception e){
            e.printStackTrace();
            String errMsg = MessageFormat.format("日志编号[{0}]更新出现异常,异常信息为[{1}]",log.getLogId(),e.getMessage());
            throw new BusinessException(errMsg);
        }
    }
}
