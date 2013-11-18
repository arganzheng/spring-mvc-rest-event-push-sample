package com.qq.ecc.openapi.eventpush.exception;

import com.qq.ecc.openapi.eventpush.common.RestErrorCode;
import com.qq.ecc.openapi.eventpush.common.RestException;

public class UnknownResourceException extends RestException {

    public UnknownResourceException(String msg){
        super(RestErrorCode.RESOURCE_NOT_FOUND, msg);
    }
}
