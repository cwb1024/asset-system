package com.chengwenbi.domain;

import com.chengwenbi.common.BaseEntity;

public class CategoryDO extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CategoryDO [");
        if (getId() != null) {
            builder.append("id=").append(getId()).append(",");
        }
        if (name != null) {
            builder.append("name=").append(name).append(",");
        }
        if (getCreateId() != null) {
            builder.append("createId=").append(getCreateId()).append(",");
        }
        if (getCreateName() != null) {
            builder.append("createName=").append(getCreateName()).append(",");
        }
        if (getCreateTime() != null) {
            builder.append("createTime=").append(getCreateTime()).append(",");
        }
        if (getModifyId() != null) {
            builder.append("modifyId=").append(getModifyId()).append(",");
        }
        if (getModifyName() != null) {
            builder.append("modifyName=").append(getModifyName()).append(",");
        }
        if (getModifyTime() != null) {
            builder.append("modifyTime=").append(getModifyTime());
        }
        builder.append("]");
        return builder.toString();
    }
}
