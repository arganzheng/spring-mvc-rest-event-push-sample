package com.qq.ecc.openapi.eventpush.common;

/**
 * RestException，自定义异常基类。定义返回格式为：errorCode和errorMessage
 * 
 * @author arganzheng
 * @date 2013-11-18
 */
public class RestException extends RuntimeException {

    private static final long serialVersionUID = -7670436661724456891L;

    private int               errorCode;

    public RestException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public RestException(int errorCode, String message, Throwable ex){
        super(message, ex);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
