package com.hwj.demo.service.impl;

import com.hwj.demo.dao.user.LoggerMapper;
import com.hwj.demo.entity.Logger;

import com.hwj.demo.exception.BusinessException;
import com.hwj.demo.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private LoggerMapper loggerMapper;
    @Override
    public void insertLog(Logger logger) {
        try {
            loggerMapper.insertLog(logger);
        }catch (Exception e){
            e.printStackTrace();
            String errMsg = MessageFormat.format("日志编号[{0}]新增出现异常,异常信息为[{1}]",logger.getId(),e.getMessage());
            throw new BusinessException(errMsg);
        }
    }
}
