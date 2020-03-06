package com.hwj.demo.service.user.Impl;

import com.hwj.demo.dao.user.UserMapper;
import com.hwj.demo.entity.User;
import com.hwj.demo.exception.BusinessException;
import com.hwj.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 根据用户账号密码查询用户
     **/
    public User queryUserByAcount(String userName, String password) throws BusinessException {
        try {
            return userMapper.queryUserByAcount(userName, password);
        } catch (Exception e){
            e.printStackTrace();
            String errMsg = MessageFormat.format("不存在该用户[{0}],异常信息为[{1}]",userName,e.getMessage());
            throw new BusinessException(errMsg);
        }
    }

    /**
     * @Description: 查询所有用户
     **/
    public List<Map<String,Object>> queryUsers() throws BusinessException {
        try {
            return userMapper.queryUsers();
        } catch (Exception e){
            throw new BusinessException("查询出现异常");
        }
    }
}
