package com.hwj.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户信息表
 * ApiModelProperty：用于字段、方法进行说明字段属性
 * ApiModel：用在返回对象上，描述返回对象的意义
 */
@ApiModel(description ="用户信息")
public class User {
    @ApiModelProperty(value = "用户ID",position = 1000)
    private String userId;
    @ApiModelProperty(value = "用户名",position = 1001)
    private String userName;
    @ApiModelProperty(value = "密码",position = 1002)
    private String password;
    @ApiModelProperty(value = "邮箱",position = 1003)
    private String userEmail;
    @ApiModelProperty(value = "用户性别",position = 1004)
    private String userSex;
    @ApiModelProperty(value = "用户状态",position = 1005)
    private String status;
    @ApiModelProperty(value = "创建时间",position = 1006)
    private String createTime;
    @ApiModelProperty(value = "更新时间",position = 1007)
    private String updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
