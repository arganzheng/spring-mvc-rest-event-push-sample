package com.qq.ecc.openapi.eventpush.common;

/**
 * REST 返回格式，这样可以利用@ResponseBody注解。
 * 
 * @author arganzheng
 * @date 2013-11-18
 */
public class RestResponse<T> {

    public static final RestResponse SUCCESS       = new RestResponse();

    public static final RestResponse ERROR_UNKNOWN = new RestResponse(RestErrorCode.UNKONW_ERROR, "系统异常！");

    private int                      errorCode;
    private String                   errorMessage;
    private T                        data;

    public RestResponse(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public RestResponse(){
    }

    public RestResponse(T data){
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
