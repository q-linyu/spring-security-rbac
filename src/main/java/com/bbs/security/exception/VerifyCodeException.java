package com.bbs.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @className: VerifyCodeException
 * @author: q-linyu
 * @description: 图片验证码异常处理类
 * @date: 2020/02/16 23:20
 * @version: 1.0
 */
public class VerifyCodeException extends AuthenticationException {

    public VerifyCodeException(String msg, Throwable t) { super(msg, t); }

    public VerifyCodeException(String msg) { super(msg); }
}
