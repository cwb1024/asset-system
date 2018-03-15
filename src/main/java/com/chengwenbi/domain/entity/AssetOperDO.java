package com.chengwenbi.domain.entity;

import java.util.Date;

/**
 * @author: chengwenbi
 * @description:资产操作表实体
 * @date:   2018/3/13 17:02
 */
public class AssetOperDO {
    private String orderId;//订单id
    private Integer type;//操作类型
    private String createrId;//创建人id
    private String createrName;//创建人姓名
    private Date createTime;//创建时间
    private Integer beforeStatus;//之前状态
    private Integer afterStatus;//之后状态
    private String operationTime;//操作时间

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

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
