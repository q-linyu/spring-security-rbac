package com.bbs.core.entity;


/**
 * 角色权限表(RoleMenu)实体类
 * @author q-linyu
 * @since 2020-02-22 19:33:23
 */
public class RoleMenu{

    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 权限ID
    */
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}