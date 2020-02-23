package com.bbs.web.controller;

import com.bbs.common.enums.HttpState;
import com.bbs.common.utils.ResponseData;
import com.bbs.core.entity.Menu;
import com.bbs.core.service.MenuService;
import com.bbs.core.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 权限表(Menu)表控制层
 * @author q-linyu
 * @since 2020-02-21 04:47:26
 */
@RestController
public class MenuController {
    /**
     * 服务对象
     */
    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;


    /**
     * 查询全部菜单 - 不递归
     * @author q-linyu
     * @description
     * @date 2020/2/21 6:26
     * @return com.bbs.common.utils.ResponseData<java.util.List<com.bbs.core.entity.Menu>>
     **/
    @RequestMapping(value = "/menus")
    @ResponseBody
    public ResponseData<List<Menu>> queryAll(){
        return ResponseData.success(HttpState.SUCCESS, menuService.selectAll());
    }

    /**
     * 根据父节点递归查询子节点 - 递归
     * @author q-linyu
     * @description
     * @date 2020/2/21 19:52
     * @Param [parentId] 父级ID
     * @return com.bbs.common.utils.ResponseData<java.util.List<com.bbs.core.entity.Menu>>
     **/
    @RequestMapping(value = "/recursive")
    @ResponseBody
    public ResponseData<List<Menu>> findChildByParentNodes(@RequestParam(value = "parentId",defaultValue = "-1")Long parentId,
                                                           @RequestParam(value = "userId",defaultValue = "1")Long userId){
        return ResponseData.success(HttpState.SUCCESS,menuService.findChildByParentNodes(parentId, userId));
    }


    /**
     * 通过主键修改数据
     * @author q-linyu
     * @description
     * @date 2020/2/22 4:07
     * @Param [menuId]
     * @return com.bbs.common.utils.ResponseData<java.lang.Void>
     **/
    @ResponseBody
    @RequestMapping(value = "/menu/update")
    public ResponseData<Void> update(Menu menu){
        menuService.update(menu);
        return ResponseData.success(HttpState.DELETE_SUCCESS);
    }

    /**
     * 通过主键删除数据
     * @author q-linyu
     * @description
     * @date 2020/2/22 4:07
     * @Param [menuId]
     * @return com.bbs.common.utils.ResponseData<java.lang.Void>
     **/
    @ResponseBody
    @RequestMapping(value = "/menu/{menuId}")
    public ResponseData<Void> delete(@PathVariable(value = "menuId")Long menuId){
        menuService.deleteById(menuId);
        return ResponseData.success(HttpState.DELETE_SUCCESS);
    }

    /**
     * 新增
     * @param roleId
     * @param menuIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/rolemenu")
    public ResponseData<Void> rolemenuUpdate(@RequestParam(value = "roleId")Long roleId,@RequestParam(value = "menuIds[]")Long[] menuIds){
        List<Long> result = new ArrayList<>(Arrays.asList(menuIds));
        roleMenuService.batchUpdate(roleId,result);
        return ResponseData.success(HttpState.UPDATE_SUCCESS);
    }

//    /**
//     * 删除
//     * @param roleId
//     * @param menuIds
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/rolemenu_delete")
//    public ResponseData<Void> rolemenuDelete(@RequestParam(value = "roleId")Long roleId,@RequestParam(value = "menuIds[]")Long[] menuIds){
//        List<Long> result = new ArrayList<>(Arrays.asList(menuIds));
//        roleMenuService.batchDelete(roleId,result);
//        return ResponseData.success(HttpState.UPDATE_SUCCESS);
//    }

}