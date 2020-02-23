package com.bbs.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户表(UserInfo)实体类
 * @author q-linyu
 * @since 2020-02-17 17:43:18
 */
public class UserInfo implements UserDetails {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实名称
     */
    private String realname;
    /**
     * 用户是否过期
     */
    private boolean accountnonexpired;
    /**
     * 用户是否锁定
     */
    private boolean accountnonlocked;
    /**
     * 证书是否过期
     */
    private boolean credentialsnonexpired;
    /**
     * 是否激活
     */
    private boolean enabled;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastTime;

    /**
     * 用户和角色的关系,多对多：表示用户所拥有的所有权限
     */
    private List<GrantedAuthority> authorities = new ArrayList<>();

    /**
     * 用户的角色
     */
    private List<Role> roles = new ArrayList<> ();


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public boolean isAccountnonexpired() {
        return accountnonexpired;
    }

    public void setAccountnonexpired(boolean accountnonexpired) {
        this.accountnonexpired = accountnonexpired;
    }

    public boolean isAccountnonlocked() {
        return accountnonlocked;
    }

    public void setAccountnonlocked(boolean accountnonlocked) {
        this.accountnonlocked = accountnonlocked;
    }

    public boolean isCredentialsnonexpired() {
        return credentialsnonexpired;
    }

    public void setCredentialsnonexpired(boolean credentialsnonexpired) {
        this.credentialsnonexpired = credentialsnonexpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountnonexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountnonlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsnonexpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", accountnonexpired=" + accountnonexpired +
                ", accountnonlocked=" + accountnonlocked +
                ", credentialsnonexpired=" + credentialsnonexpired +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                ", lastTime=" + lastTime +
                ", authorities=" + authorities +
                ", roles=" + roles +
                '}';
    }
}