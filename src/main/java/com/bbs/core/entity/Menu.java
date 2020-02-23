package com.bbs.core.entity;


import java.util.List;

/**
 * 权限表(Menu)实体类
 * @author q-linyu
 * @since 2020-02-21 04:47:26
 */
public class Menu{

    /**
    * 权限ID
    */
    private Long menuId;
    /**
    * 权限名
    */
    private String menuname;
    /**
    * 权限标识符
    */
    private String identifier;

    /**
     * 权限图标
     */
    private String menuIcon;
    /**
    * 菜单url
    */
    private String menuUrl;
    /**
    * 父级ID
    */
    private Long parentId;

    /**
     * 是否是按钮，-1表示目录，1表示一级菜单，2表示二级菜单，3表示按钮
     */
    private Integer isMenu;

    /**
     * 子节点
     */
    private List<Menu> childs;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public List<Menu> getChilds() {
        return childs;
    }

    public void setChilds(List<Menu> childs) {
        this.childs = childs;
    }


}