package com.chengwenbi.domain.entity;

import com.chengwenbi.common.BaseEntity;

public class OrderDO extends BaseEntity {

    private String id;
    private Integer state;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
