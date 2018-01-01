package com.chengwenbi.domain;

import com.chengwenbi.common.BaseEntity;

public class AssetOperDO extends BaseEntity {
    private String assetId;
    private Integer operType;//操作类型[0；资产添加,1:资产删除,2:资产修改,3:借出申请，4：借出批准，5：借出归还，6：资产报废]
    private Integer quantity;
    private String remark;
    private String approver;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AssetOperDO [)");
        if (getId() != null) {
            builder.append("id=").append(getId()).append(",");
        }
        if (assetId != null) {
            builder.append("assetId=").append(assetId).append(",");
        }
        if (operType != null) {
            builder.append("operType=").append(operType).append(",");
        }
        if (quantity != null) {
            builder.append("quantity=").append(quantity).append(",");
        }
        if (remark != null) {
            builder.append("remark=").append(remark).append(",");
        }
        if (approver != null) {
            builder.append("approver=").append(approver).append(",");
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
