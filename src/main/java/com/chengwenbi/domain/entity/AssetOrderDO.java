package com.chengwenbi.domain.entity;

import com.chengwenbi.common.BaseEntity;

import java.util.Date;

/**
 * @description:资产记录表（一个借出记录代表一个订单）
 * @author: chengwenbi
 * @date:   2018/3/13 17:03
 */
public class AssetOrderDO extends BaseEntity {
    private Integer type;
    private Integer status;
    private String nowUserId;
    private String nowUserName;
    private Date nowTime;
    private String remark;
    private String approverId;
    private String approverName;
    private Date approveTime;
    private String propApproverId;
    private String propApproverName;
    private Date repayTime;

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

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }
}
