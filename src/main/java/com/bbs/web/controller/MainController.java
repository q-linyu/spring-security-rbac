package com.bbs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @className: MainController
 * @author: q-linyu
 * @description: 页面跳转
 * @date: 2020/02/17 18:04
 * @version: 1.0
 */
@Controller
public class MainController {

    /**
     * 根据页面路径跳转
     * @author q-linyu
     * @date 2020/2/17 18:06
     * @param page 页面
     * @return java.lang.String
     */
    @RequestMapping(value = "/{page}.html")
    public String mainPage(@PathVariable(value = "page")String page){
        return page;
    }

    /**
     * 用户模块页面跳转
     * @author q-linyu
     * @date 2020/2/17 18:06
     * @param page 页面
     * @return java.lang.String
     */
    @RequestMapping(value = "/user/{page}.html")
    public String userPage(@PathVariable(value = "page")String page){
        return "/user/" + page;
    }

    /**
     * 系统管理模块页面跳转
     * @author q-linyu
     * @date 2020/2/17 18:06
     * @param page 页面
     * @return java.lang.String
     */
    @RequestMapping(value = "/system/{page}.html")
    public String systemPage(@PathVariable(value = "page")String page){
        return "/system/" + page;
    }

    /**
     * 权限模块页面跳转
     * @author q-linyu
     * @date 2020/2/17 18:06
     * @param page 页面
     * @return java.lang.String
     */
    @RequestMapping(value = "/permission/{page}.html")
    public String permissionPage(@PathVariable(value = "page")String page){
        return "/permission/" + page;
    }

    /**
     * 角色管理模块页面跳转
     * @author q-linyu
     * @date 2020/2/17 18:06
     * @param page 页面
     * @return java.lang.String
     */
    @RequestMapping(value = "/role/{page}.html")
    public String rolePage(@PathVariable(value = "page")String page){
        return "/role/" + page;
    }


    /**
     * 系统设置模块页面跳转
     * @author q-linyu
     * @date 2020/2/17 18:06
     * @param page 页面
     * @return java.lang.String
     */
    @RequestMapping(value = "/settings/{page}.html")
    public String settingsPage(@PathVariable(value = "page")String page){
        return "/settings/" + page;
    }
}
