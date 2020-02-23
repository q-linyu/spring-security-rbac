package com.bbs.core.service;

import com.bbs.core.entity.UserRole;

import java.util.List;

/**
 * 用户角色表(UserRole)表服务接口
 *
 * @author makejava
 * @since 2020-02-19 17:00:33
 */
public interface UserRoleService {

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole userRole);



}