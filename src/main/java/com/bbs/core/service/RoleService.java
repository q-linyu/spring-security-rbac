package com.bbs.core.service;

import com.bbs.core.entity.Role;
import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author makejava
 * @since 2020-02-17 17:43:07
 */
public interface RoleService {

    /**
     * 根据对象查询全部角色信息
     * @return
     */
    List<Role> queryAll();

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    Role queryById(Long roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}