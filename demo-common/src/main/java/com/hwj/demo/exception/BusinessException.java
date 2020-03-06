package com.hwj.demo.exception;




/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：自定义业务异常类
 */

public class BusinessException extends RuntimeException{

    private StatusCode statusCode;
    private String message;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(StatusCode statusCode,String message) {
        super(message);
        this.statusCode =statusCode;

    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }


}
