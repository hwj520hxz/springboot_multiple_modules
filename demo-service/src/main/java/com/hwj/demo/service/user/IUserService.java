package com.hwj.demo.service.user;


import com.hwj.demo.entity.User;
import com.hwj.demo.exception.BusinessException;


/**
 * @author 创建人：何伟杰
 * @version 版本号：V1.0
 * @ClassName 接口名：IUserService
 * @Description 功能说明：
 * @date 创建日期：2020/1/10
 * @time 创建时间：16:14
 */
public interface IUserService {
    User queryUserByAcount(String userName, String password) throws BusinessException;

}
