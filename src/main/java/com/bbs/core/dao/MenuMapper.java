package com.bbs.core.dao;

import com.bbs.core.entity.Menu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 权限表(Menu)表数据库访问层
 * @author q-linyu
 * @since 2020-02-21 04:47:26
 */
public interface MenuMapper {

    /**
     * 查询全部菜单
     * @return
     */
    List<Menu> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Long menuId);

    /**
     * 根据用户ID动态获取左侧菜单
     * @param userId
     * @return
     */
    List<Menu> queryAllByMenuId(@Param(value = "userId") Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param menu 实例对象
     * @return 对象列表
     */
    List<Menu> queryAll(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Long menuId);

}