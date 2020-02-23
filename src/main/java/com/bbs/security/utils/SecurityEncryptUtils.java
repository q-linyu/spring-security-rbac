package com.bbs.security.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @className: SecurityEncryptUtils
 * @author: q-linyu
 * @description: Scurity密码加密工具类
 * @date: 2020/02/17 22:52
 * @version: 1.0
 */
public class SecurityEncryptUtils {

    /**
     * SpringScurity密码加密
     * @author q-linyu
     * @date 2020/2/17 22:53
     * @param password
     * @return java.lang.String
     */
    public static String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 校验原密码是否匹配
     * @author q-linyu
     * @date 2020/2/18 0018 16:51
     * @param oldpassword
     * @param password
     * @return boolean
     */
    public static boolean matchesPassword(String oldpassword,String password){
        return new BCryptPasswordEncoder().matches(oldpassword,password);
    }


}
