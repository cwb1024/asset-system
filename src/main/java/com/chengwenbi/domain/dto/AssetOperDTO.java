package com.chengwenbi.domain.dto;

import com.chengwenbi.common.BaseEntity;

public class AssetOperDTO extends BaseEntity {
    private String id;
    private String orderId;//订单id
    private Integer type;//操作类型
    private Integer beforeStatus;//之前状态
    private Integer afterStatus;//之后状态
    private String operationTime;//操作时间
    private Integer account;
    private Integer originalCount;
    private String jsonStr;//json数组，存储多个产品数据
    private String applierName;
    private String applierId;
    private String remark;
    private String propApproverId;
    private String propApproverName;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(Integer beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public Integer getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Integer afterStatus) {
        this.afterStatus = afterStatus;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getOriginalCount() {
        return originalCount;
    }

    public void setOriginalCount(Integer originalCount) {
        this.originalCount = originalCount;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public String getApplierId() {
        return applierId;
    }

    public void setApplierId(String applierId) {
        this.applierId = applierId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPropApproverId() {
        return propApproverId;
    }

    public void setPropApproverId(String propApproverId) {
        this.propApproverId = propApproverId;
    }

    public String getPropApproverName() {
        return propApproverName;
    }

    public void setPropApproverName(String propApproverName) {
        this.propApproverName = propApproverName;
    }
}
