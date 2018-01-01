package com.chengwenbi.domain;

import com.chengwenbi.common.BaseEntity;

public class AssetInfoDO extends BaseEntity {
    private String name;
    private String categoryId;
    private Integer amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AssetInfoDO [");
        if (getId() != null) {
            builder.append("id=").append(getId()).append(",");
        }
        if (name != null) {
            builder.append("name=").append(name).append(",");
        }
        if (categoryId != null) {
            builder.append("categoryId=").append(categoryId);
        }
        if (amount != null) {
            builder.append("amount=").append(amount).append(",");
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
