package com.chengwenbi.domain.vo;

import com.chengwenbi.domain.entity.MenuDO;

import java.util.List;

public class MenuVO {

    private MenuDO parentMenu;
    private List<MenuDO> childrenMenu;

    public MenuDO getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuDO parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<MenuDO> getChildrenMenu() {
        return childrenMenu;
    }

    public void setChildrenMenu(List<MenuDO> childrenMenu) {
        this.childrenMenu = childrenMenu;
    }
}
