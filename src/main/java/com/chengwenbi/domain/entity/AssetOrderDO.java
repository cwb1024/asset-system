package com.chengwenbi.domain.entity;

import com.chengwenbi.common.BaseEntity;

import java.util.Date;

/**
 * @description:资产记录表（一个借出记录代表一个订单）
 * @author: chengwenbi
 * @date:   2018/3/13 17:03
 */
public class AssetOrderDO extends BaseEntity {
    private String assetNo;//资产编号
    private String name;
    private Integer type;
    private Integer status;
    private String categoryId;
    private String serialNumber;
    private String nowUserId;
    private String nowUserName;
    private String nowStatusName;
    private String remark;
    private String applierId;
    private String applierName;
    private Date applyTime;
    private String approverId;
    private String approverName;
    private Date approveTime;

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNowUserId() {
        return nowUserId;
    }

    public void setNowUserId(String nowUserId) {
        this.nowUserId = nowUserId;
    }

    public String getNowUserName() {
        return nowUserName;
    }

    public void setNowUserName(String nowUserName) {
        this.nowUserName = nowUserName;
    }

    public String getNowStatusName() {
        return nowStatusName;
    }

    public void setNowStatusName(String nowStatusName) {
        this.nowStatusName = nowStatusName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplierId() {
        return applierId;
    }

    public void setApplierId(String applierId) {
        this.applierId = applierId;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }
}
