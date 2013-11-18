package com.qq.ecc.openapi.eventpush.exception;

import com.qq.ecc.openapi.eventpush.common.RestErrorCode;
import com.qq.ecc.openapi.eventpush.common.RestException;

/**
 * 签名错误
 * 
 * @author arganzheng
 * @date 2013-11-18
 */
public class AuthorizationException extends RestException {

    private static final long serialVersionUID = -3213709556779117944L;

    public AuthorizationException(int errorCode, String message){
        super(errorCode, message);
    }

    public AuthorizationException(String message){
        this(RestErrorCode.AUTHENTICATION_FAILED, message);
    }

}
