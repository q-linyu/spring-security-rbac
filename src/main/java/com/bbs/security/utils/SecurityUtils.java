package com.bbs.security.utils;

import com.bbs.core.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @className: SecurityUtils
 * @author: q-linyu
 * @description: SpringSecurity工具类
 * @date: 2020/02/17 22:53
 * @version: 1.0
 */
public class SecurityUtils {

    public static UserInfo getUserInfo(){
        UserInfo userInfo = null;
        // 获得登录后的用户
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(principal)){
            if (principal instanceof UserInfo){
                userInfo = (UserInfo) principal;
            }
        }
        return userInfo;
    }

}
