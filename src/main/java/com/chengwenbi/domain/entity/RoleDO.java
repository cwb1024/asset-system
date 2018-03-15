package com.chengwenbi.domain.entity;

import com.chengwenbi.common.BaseEntity;

/**
 * @description:角色表实体
 * @author: chengwenbi
 * @date:   2018/3/13 17:04
 */
public class RoleDO extends BaseEntity {
    private String name;
    private Integer state;
    private Integer sort;
    private String remark;
    private String isDefault;//是否默认选中

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
