package com.bbs.core.service.impl;

import com.bbs.common.utils.Builder;
import com.bbs.core.entity.RoleMenu;
import com.bbs.core.dao.RoleMenuMapper;
import com.bbs.core.service.RoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限表(RoleMenu)表服务实现类
 * @author q-linyu
 * @since 2020-02-22 19:33:23
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param  roleId 主键
     * @return 实例对象
     */
    @Override
    public RoleMenu queryById(Long roleId) {
        return this.roleMenuMapper.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleMenu> queryAllByLimit(int offset, int limit) {
        return this.roleMenuMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 修改
     * @param roleId
     * @param menuIds
     */
    @Override
    public void batchUpdate(Long roleId, List<Long> menuIds) {
        roleMenuMapper.delete(roleId);
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu roleMenu = Builder.of(RoleMenu::new)
                    .with(RoleMenu::setRoleId,roleId)
                    .with(RoleMenu::setMenuId,menuId)
                    .build();
            roleMenus.add(roleMenu);
        }
        roleMenuMapper.batchInsert(roleMenus);
    }

}