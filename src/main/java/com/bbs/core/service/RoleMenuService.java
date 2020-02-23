package com.bbs.core.service;

import com.bbs.core.entity.RoleMenu;
import java.util.List;

/**
 * 角色权限表(RoleMenu)表服务接口
 * @author q-linyu
 * @since 2020-02-22 19:33:23
 */
public interface RoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleMenu queryById(Long roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RoleMenu> queryAllByLimit(int offset, int limit);

//    /**
//     * 批量新增
//     * @param roleId
//     * @param menuIds
//     */
//    void batchInsert(Long roleId, List<Long> menuIds);
//
//    /**
//     * 修改
//     * @param roleId
//     * @param menuIds
//     */
//    void batchDelete(Long roleId, List<Long> menuIds);

    /**
     * 修改
     * @param roleId
     * @param menuIds
     */
    void batchUpdate(Long roleId, List<Long> menuIds);
}