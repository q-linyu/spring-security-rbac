package com.bbs.core.service.impl;

import com.bbs.common.enums.HttpState;
import com.bbs.common.exception.BusinessRuntimeException;
import com.bbs.common.utils.Builder;
import com.bbs.common.utils.PageHepler;
import com.bbs.common.utils.PageInfo;
import com.bbs.core.dao.UserRoleMapper;
import com.bbs.core.entity.Menu;
import com.bbs.core.entity.UserInfo;
import com.bbs.core.dao.UserMapper;
import com.bbs.core.entity.UserRole;
import com.bbs.core.service.UserService;
import com.bbs.security.utils.SecurityEncryptUtils;
import com.bbs.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户表(UserInfo)表服务实现类
 * @author q-linyu
 * @since 2020-02-17 17:43:18
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 登录
     * @author q-linyu
     * @date 2020/2/17 22:39
     * @param username 传入的用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查询用户信息是否存在
        UserInfo userInfo = userMapper.queryByUsername(username);
        if (Objects.isNull(userInfo)){
            throw new BusinessRuntimeException("用户名不存在");
        }

        // 根据用户名查询所有该用户的权限
        List<Menu> menus = userMapper.queryMenuByUsername(username);

        // 定义一个集合存储权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        menus.forEach(menu -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(menu.getIdentifier());
            authorities.add(authority);
        });

        // 把所有权限赋值给userInfo
        userInfo.setAuthorities(authorities);
        return userInfo;
    }

    /**
     * 修改密码
     * @param oldpassword
     * @param newPassword
     */
    @Override
    public void updatePassword(String oldpassword,String newPassword) {
        boolean bool = SecurityEncryptUtils.matchesPassword(oldpassword, SecurityUtils.getUserInfo().getPassword());
        if (!bool){
            throw new BusinessRuntimeException(HttpState.OLD_PASSWORD_FAILURE);
        }
        long id = SecurityUtils.getUserInfo().getUserId();
        Integer count = userMapper.updatePassword(id, SecurityEncryptUtils.encryptPassword(newPassword));
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.PASSWORD_UPDATE_FAILURE);
        }
    }

    /**
     * 查询全部系统用户带角色的分页信息
     * @author q-linyu
     * @date 2020/2/19 0019 15:00
     * @param username
     * @param createTime
     * @param pageHepler
     * @return com.bbs.common.utils.PageInfo<java.util.List<com.bbs.core.entity.UserInfo>>
     */
    @Override
    public PageInfo<List<UserInfo>> page(String username, String createTime, PageHepler pageHepler) {
        List<UserInfo> userInfos = userMapper.queryByPage(username,createTime,pageHepler);
        if (CollectionUtils.isEmpty(userInfos)){
            throw new BusinessRuntimeException(HttpState.NOT_FONT);
        }
        int count = userMapper.count(username,createTime);
        return new PageInfo<>(count,userInfos);
    }

    /**
     * 新增用户
     * @author q-linyu
     * @date 2020/2/19 17:09
     * @param userInfo
     * @param roleId
     * @return void
     */
    @Override
    public void addUserInfo(UserInfo userInfo, Long roleId) {
        // 默认值
        userInfo.setPassword("123456");
        userInfo.setRealname(userInfo.getUsername());

        Integer userCount = userMapper.insert(userInfo);
        if (userCount <= 0){
            throw new BusinessRuntimeException(HttpState.CREATE_FAILURE);
        }

        // 关联中间表
        UserRole userRole = Builder.of(UserRole::new)
                .with(UserRole::setUserId,userInfo.getUserId())
                .with(UserRole::setRoleId,roleId)
                .build();

        int userRoleCount = userRoleMapper.insert(userRole);
        if (userRoleCount <= 0){
            throw new BusinessRuntimeException(HttpState.CREATE_FAILURE);
        }
    }

    /**
     * 通过ID查询单条数据
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public UserInfo get(Long userId) {
        UserInfo userInfo = userMapper.queryById(userId);
        if (Objects.isNull(userInfo)){
            throw new BusinessRuntimeException(HttpState.NOT_FONT);
        }
        return userInfo;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserInfo> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 修改数据
     * @author q-linyu
     * @description
     * @date 2020/2/20 17:23
     * @param userInfo 实例对象
     * @param roleId
     * @return void
     **/
    @Override
    public void update(UserInfo userInfo, Long roleId) {
        int count = userMapper.update(userInfo);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILURE);
        }
        // 把中间表也删除
        UserRole userRole = Builder.of(UserRole::new)
                .with(UserRole::setUserId,userInfo.getUserId())
                .with(UserRole::setRoleId,roleId)
                .build();
        int userRoleCount = userRoleMapper.update(userRole);
        if (userRoleCount <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILURE);
        }
    }


    /**
     * 通过主键删除数据
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(Long userId) {
        int count = userMapper.deleteById(userId);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.DELETE_FAILURE);
        }
        // 把中间表也删除
        int userRoleCount = userRoleMapper.deleteById(userId);
        if (userRoleCount <= 0){
            throw new BusinessRuntimeException(HttpState.DELETE_FAILURE);
        }
    }


}