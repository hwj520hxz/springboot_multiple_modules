package com.hwj.demo.exception;

public enum StatusCode {
    Success(200, "成功！"),
    Error(201, "失败！"),
    Exception(500,"发生异常！"),
    Forbidden(401,"禁止访问！");
    private Integer code;
    private String message;

     StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public  Integer getCode (){
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
