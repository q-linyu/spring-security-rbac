package com.bbs.web.controller;

import com.bbs.core.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户角色表(UserRole)表控制层
 *
 * @author makejava
 * @since 2020-02-19 17:00:33
 */
@RestController
public class UserRoleController {

    /**
     * 服务对象
     */
    @Autowired
    private UserRoleService tblUserRoleService;



}