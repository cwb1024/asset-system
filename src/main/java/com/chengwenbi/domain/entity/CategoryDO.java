package com.chengwenbi.domain.entity;

import com.chengwenbi.common.BaseEntity;

/**
 * @description:资产类别表实体
 * @author: chengwenbi
 * @date:   2018/3/13 17:03
 */

public class CategoryDO extends BaseEntity {
    private String id;
    private String name;
    private String spec;
    private String model;
    private String brand;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
