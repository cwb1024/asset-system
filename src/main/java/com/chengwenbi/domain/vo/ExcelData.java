package com.chengwenbi.domain.vo;

import java.util.Date;

public class ExcelData {
    private Integer type;
    private String orderId;
    private String name;
    private String categoryName;
    private Integer account;
    private String createName;
    private String approverName;
    private Date createTime;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "type=" + type +
                ", orderId='" + orderId + '\'' +
                ", name='" + name + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", account=" + account +
                ", createName='" + createName + '\'' +
                ", approverName='" + approverName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
