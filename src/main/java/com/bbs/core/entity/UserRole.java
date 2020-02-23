package com.bbs.core.entity;


/**
 * 用户角色表(UserRole)实体类
 * @author q-linyu
 * @since 2020-02-19 17:00:33
 */
public class UserRole {

    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 角色ID
    */
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}