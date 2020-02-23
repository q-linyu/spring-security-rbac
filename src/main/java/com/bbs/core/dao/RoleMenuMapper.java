package com.bbs.core.dao;

import com.bbs.core.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 角色权限表(RoleMenu)表数据库访问层
 * @author q-linyu
 * @since 2020-02-22 19:33:23
 */
public interface RoleMenuMapper {



    /**
     * 通过ID查询单条数据
     * @param roleId 主键
     * @return 实例对象
     */
    RoleMenu queryById(Long roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RoleMenu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param roleMenu 实例对象
     * @return 对象列表
     */
    List<RoleMenu> queryAll(RoleMenu roleMenu);

    /**
     * 批量新增数据
     * @param roleMenus
     * @return 影响行数
     */
    Integer batchInsert(@Param(value = "roleMenus") List<RoleMenu> roleMenus);

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 影响行数
     */
    int update(RoleMenu roleMenu);

    /**
     * 批量删除
     * @param roleId
     * @return
     */
    Integer delete(@Param(value = "roleId") Long roleId);

}