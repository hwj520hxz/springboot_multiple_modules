package com.hwj.demo.service.user;


import com.hwj.demo.entity.User;
import com.hwj.demo.exception.BusinessException;

import java.util.List;
import java.util.Map;


/**
 * @author 创建人：何伟杰
 * @version 版本号：V1.0
 * @Description 功能说明：用户服务接口
 */
public interface IUserService {
    /**
     * @Description: 根据用户账号密码查询用户
     **/
    User queryUserByAcount(String userName, String password) throws BusinessException;
    /**
     * @Description: 查询所有用户
     **/
    List<Map<String,Object>> queryUsers() throws BusinessException;

}
