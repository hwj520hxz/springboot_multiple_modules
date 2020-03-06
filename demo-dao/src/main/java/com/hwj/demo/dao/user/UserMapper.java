package com.hwj.demo.dao.user;

import com.hwj.demo.entity.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户表mapper
 */
public interface UserMapper {
    /**
     * 功能说明：查询所有用户
     **/
    List<Map<String, Object>> queryUsers();

    User queryUserByAcount(@Param("userName") String userName, @Param("password") String password);
}
