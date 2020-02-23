package com.bbs.security.handler;
import	java.io.PrintWriter;

import com.bbs.common.utils.ResponseData;
import com.bbs.security.exception.VerifyCodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: AuthenticationFailureHandlerImpl
 * @author: q-linyu
 * @description: 登录失败处理器
 * @date: 2020/02/17 21:58
 * @version: 1.0
 */
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 自定义处理逻辑
     * @author q-linyu
     * @date 2020/2/17 22:13
     * @param request
     * @param response
     * @param e
     * @return void
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        // 设置字符集编码
        response.setContentType("text/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        ResponseData<Object> responseData = ResponseData.error(e.getMessage());
        // 验证码自定义异常的处理
        if (e instanceof VerifyCodeException){
            responseData.setMsg(e.getMessage());
        }else if (e instanceof LockedException){
            responseData.setMsg("账户被锁定,请联系管理员!");
        }else if (e instanceof CredentialsExpiredException){
            responseData.setMsg("密码过期,请联系管理员!");
        }else if (e instanceof AccountExpiredException){
            responseData.setMsg("账户过期,请联系管理员!");
        }else if (e instanceof DisabledException){
            responseData.setMsg("账户被禁用,请联系管理员!");
        }else if (e instanceof BadCredentialsException){
            responseData.setMsg("用户名密码输入错误,请重新输入!");
        }

        // 序列化
        String json = objectMapper.writeValueAsString(responseData);
        out.write(json);
        out.flush();
        out.close();
    }
}
