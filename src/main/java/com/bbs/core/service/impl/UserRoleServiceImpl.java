package com.bbs.core.service.impl;

import com.bbs.core.entity.UserRole;
import com.bbs.core.dao.UserRoleMapper;
import com.bbs.core.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-02-19 17:00:33
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryAllByLimit(int offset, int limit) {
        return this.userRoleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole userRole) {
        this.userRoleMapper.insert(userRole);
        return userRole;
    }


}