package com.hwj.demo.web.user;

import com.hwj.demo.annotations.LoggerOperator;
import com.hwj.demo.annotations.SystemControllerLog;
import com.hwj.demo.data.ResponseResult;
import com.hwj.demo.entity.User;
import com.hwj.demo.util.MD5Utils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：使用shiro进行登录
 */
@Api(tags = "用户登录接口")
@RestController
public class LoginController {
    @PostMapping(value = "/login")
//    @LoggerOperator(description = "用户登录")
    @SystemControllerLog(description = "用户登录")
    public ResponseResult login(String username, String password) {

        try {
            password = MD5Utils.string2MD5(password);
            //password = DigestUtils.md5DigestAsHex((password).getBytes("utf-8"));
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("找不到md5算法");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResponseResult.error(e.getMessage());
        }catch (LockedAccountException e) {
            return ResponseResult.error(e.getMessage());
        } catch (DisabledAccountException e) {
            return ResponseResult.error(e.getMessage());
        }
        User user = (User) subject.getPrincipal();
        subject.getSession().setAttribute("user", user);
        return ResponseResult.success(user);
    }

}
