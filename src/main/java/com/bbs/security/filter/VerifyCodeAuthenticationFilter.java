package com.bbs.security.filter;

import com.bbs.common.enums.HttpState;
import com.bbs.common.exception.BusinessRuntimeException;
import com.bbs.security.exception.VerifyCodeException;
import com.bbs.security.handler.AuthenticationFailureHandlerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: VerifyCodeAuthenticationFilter
 * @author: q-linyu
 * @description: 图片验证码过滤器
 * @date: 2020/02/17 21:55
 * @version: 1.0
 */
public class VerifyCodeAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    public void setAuthenticationFailureHandler(AuthenticationFailureHandlerImpl authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    /**
     * 当所有请求经过的时候会经过该过滤器
     * @author q-linyu
     * @date 2020/2/17 21:56
     * @param request
     * @param response
     * @param filterChain
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String url = "/login";
        final String method = "post";

        // 判断当前请求是否是登陆请求,如果是才校验
        if (StringUtils.equals(url,request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),method)){
            try {
                // 获取用户输入的验证码
                final String verifyCode = request.getParameter("verifyCode");
                // 判断验证码是否为空
                if ("".equals(verifyCode)){
                    throw new VerifyCodeException("验证码不能为空");
                }

                // 从系统中获取验证码
                String key = (String) request.getSession().getAttribute("key");
                if (StringUtils.isBlank(key)){
                    throw new VerifyCodeException("验证码过期");
                }

                // 判断输入的验证码和系统的验证码是否一致
                if (!verifyCode.trim().toLowerCase().equals(key.trim().toLowerCase())){
                    throw new VerifyCodeException("验证码不一致");
                }

            }catch (AuthenticationException e){
                // 交给UserAuthenticationSuccessHandler来处理
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        // 否则其他请求都放行
        filterChain.doFilter(request,response);
    }
}
