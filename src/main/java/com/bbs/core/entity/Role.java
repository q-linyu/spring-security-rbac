package com.bbs.core.entity;


/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2020-02-17 17:43:07
 */
public class Role{

    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 角色名称
    */
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}