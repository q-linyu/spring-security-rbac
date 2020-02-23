package com.bbs.common.exception;

import com.bbs.common.enums.HttpState;
import org.springframework.security.core.AuthenticationException;

/**
 * @className: BusinessRuntimeException
 * @author: q-linyu
 * @description: 业务异常处理类
 * @date: 2020/02/17 22:01
 * @version: 1.0
 */
public class BusinessRuntimeException extends AuthenticationException {

    private HttpState httpState;

    public BusinessRuntimeException(HttpState httpState) {
        super(httpState.getMsg());
        this.httpState = httpState;
    }

    public BusinessRuntimeException(String msg, Throwable t) {
        super(msg, t);
    }

    public BusinessRuntimeException(String msg) {
        super(msg);
    }

    public HttpState getHttpState() {
        return httpState;
    }

    public void setHttpState(HttpState httpState) {
        this.httpState = httpState;
    }
}
