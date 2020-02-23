package com.bbs.core.service.impl;

import com.bbs.common.enums.HttpState;
import com.bbs.common.exception.BusinessRuntimeException;
import com.bbs.core.entity.Role;
import com.bbs.core.dao.RoleMapper;
import com.bbs.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author makejava
 * @since 2020-02-17 17:43:07
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据对象查询全部角色信息
     * @author q-linyu
     * @date 2020/2/19 16:32
     * @return
     */
    @Override
    public List<Role> queryAll() {
        List<Role> roles = roleMapper.queryAll(null);
        if (CollectionUtils.isEmpty(roles)) {
            throw new BusinessRuntimeException(HttpState.NOT_FONT);
        }
        return roles;
    }



    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long roleId) {
        return this.roleMapper.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleMapper.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleMapper.update(role);
        return this.queryById(role.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.roleMapper.deleteById(roleId) > 0;
    }
}