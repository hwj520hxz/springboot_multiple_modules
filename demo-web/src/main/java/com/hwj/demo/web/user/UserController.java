package com.hwj.demo.web.user;

import com.hwj.demo.data.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户信息-控制层
 */

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("/queryUsers")
    public ResponseResult queryUsers(){
        try {
            return ResponseResult.success("成功");
        }catch (Exception e){
            return ResponseResult.error();
        }
    }
}
