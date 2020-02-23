package com.bbs.core.service;

import com.bbs.core.entity.Menu;

import java.util.List;

/**
 * 权限表(Menu)表服务接口
 * @author q-linyu
 * @since 2020-02-21 04:47:26
 */
public interface MenuService {

    /**
     * 查询全部菜单
     * @return
     */
    List<Menu> selectAll();

    /**
     * 根据父节点递归查询子节点
     * @param parentId
     * @param userId
     * @return
     */
    List<Menu> findChildByParentNodes(Long parentId,Long userId);


    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Long menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    void update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    void deleteById(Long menuId);

}