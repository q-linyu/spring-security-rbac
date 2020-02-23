package com.bbs.core.service.impl;

import com.bbs.common.enums.HttpState;
import com.bbs.common.exception.BusinessRuntimeException;
import com.bbs.common.utils.Builder;
import com.bbs.core.entity.Menu;
import com.bbs.core.dao.MenuMapper;
import com.bbs.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限表(Menu)表服务实现类
 *
 * @author q-linyu
 * @since 2020-02-21 04:47:26
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询全部菜单
     *
     * @return java.util.List<com.bbs.core.entity.Menu>
     * @author q-linyu
     * @description
     * @date 2020/2/21 6:24
     **/
    @Override
    public List<Menu> selectAll() {
        List<Menu> menus = menuMapper.selectAll();
        if (CollectionUtils.isEmpty(menus)) {
            throw new BusinessRuntimeException(HttpState.NOT_FONT);
        }
        return menus;
    }

    /**
     * 根据父节点递归查询子节点
     * @return java.util.List<com.bbs.core.entity.Menu>
     * @author q-linyu
     * @description
     * @date 2020/2/21 19:41
     * @Param parentId
     **/
    @Override
    public List<Menu> findChildByParentNodes(Long parentId,Long userId) {
        // 查询全部菜单
        List<Menu> menus = menuMapper.queryAllByMenuId(userId);
        if (CollectionUtils.isEmpty(menus)) {
            throw new BusinessRuntimeException(HttpState.NOT_FONT);
        }
        return recursiveQueryChirlNodes(parentId, menus);
    }

    /**
     * 递归查询所有子节点
     *
     * @return java.util.List<com.bbs.core.entity.Menu>
     * @author q-linyu
     * @description
     * @date 2020/2/21 0021 19:42
     * @Param parentId
     * @Param menus
     **/
    private List<Menu> recursiveQueryChirlNodes(Long parentId, List<Menu> menus) {
        List<Menu> childNods = new ArrayList<>();
        for (Menu menu : menus) {
            Menu record = Builder.of(Menu::new)
                    .with(Menu::setMenuId, menu.getMenuId())
                    .with(Menu::setMenuname, menu.getMenuname())
                    .with(Menu::setIdentifier, menu.getIdentifier())
                    .with(Menu::setMenuIcon, menu.getMenuIcon())
                    .with(Menu::setMenuUrl, menu.getMenuUrl())
                    .with(Menu::setParentId, menu.getParentId())
                    .with(Menu::setIsMenu, menu.getIsMenu())
                    .build();

            if (record.getParentId().equals(parentId)) {
                // 获得子节点
                if (record.getMenuId() != 0) {
                    record.setChilds(recursiveQueryChirlNodes(record.getMenuId(), menus));
                }
                childNods.add(record);
            }
        }
        return childNods;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Long menuId) {
        return this.menuMapper.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Menu> queryAllByLimit(int offset, int limit) {
        return this.menuMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu insert(Menu menu) {
        this.menuMapper.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public void update(Menu menu) {
        int count = menuMapper.update(menu);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILURE);
        }
    }

    /**
     * 通过主键删除数据
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(Long menuId) {
        int count = menuMapper.deleteById(menuId);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.DELETE_FAILURE);
        }

    }
}