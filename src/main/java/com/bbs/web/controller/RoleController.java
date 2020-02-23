package com.bbs.web.controller;


import com.bbs.common.enums.HttpState;
import com.bbs.common.utils.ResponseData;
import com.bbs.core.entity.Role;
import com.bbs.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色表(Role)表控制层
 *
 * @author makejava
 * @since 2020-02-17 17:43:07
 */
@Controller
public class RoleController {
    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;

    /**
     * 查询全部角色
     * @author q-linyu
     * @date 2020/2/19 16:34
     * @param
     * @return com.bbs.common.utils.ResponseData<java.util.List<com.bbs.core.entity.Role>>
     */
    @RequestMapping(value = "/api/roles")
    @ResponseBody
    public ResponseData<List<Role>> selectAll(){
        return ResponseData.success(HttpState.SUCCESS,roleService.queryAll());
    }




}