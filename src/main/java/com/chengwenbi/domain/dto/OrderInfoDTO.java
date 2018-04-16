package com.chengwenbi.domain.dto;

import com.chengwenbi.common.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoDTO extends BaseEntity {

    private String name;
    private Integer account;//资产数目
    private Integer type;
    private Integer status;
    private String serialNumber;
    private String nowUserId;
    private String nowUserName;
    private Date nowTime;
    private String remark;
    private String approverId;
    private String approverName;
    private Date approveTime;
    private Integer operFlag;
    private String propApproverId;
    private String propApproverName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public Integer getOperFlag() {
        return operFlag;
    }

    public void setOperFlag(Integer operFlag) {
        this.operFlag = operFlag;
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
