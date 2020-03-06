package com.hwj.demo.web.user;

import com.hwj.demo.data.ResponseResult;
import com.hwj.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户信息-控制层
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/queryUsers")
    public ResponseResult queryUsers(){
        try {
            List<Map<String,Object>> users = userService.queryUsers();
            return ResponseResult.success(users);
        }catch (Exception e){
            return ResponseResult.error(e.getMessage());
        }
    }
}
