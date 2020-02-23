package com.bbs.security.handler;

import com.bbs.common.enums.HttpState;
import com.bbs.common.utils.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: AuthenticationSuccessHandlerImpl
 * @author: q-linyu
 * @description: 登录成功处理器
 * @date: 2020/02/17 22:42
 * @version: 1.0
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 自定义处理逻辑
     * @author q-linyu
     * @date 2020/2/17 0017 22:43
     * @param request
     * @param response
     * @param authentication
     * @return void
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseData<Object> responseData = ResponseData.success(HttpState.SUCCESS,authentication);
        String json = objectMapper.writeValueAsString(responseData);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
