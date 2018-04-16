package com.chengwenbi.domain.entity;

import com.chengwenbi.common.BaseEntity;

import java.util.Date;

/**
 * @author: chengwenbi
 * @description:资产操作表实体
 * @date:   2018/3/13 17:02
 */
public class AssetOperDO extends BaseEntity {
    private String orderId;//订单id
    private Integer type;//操作类型
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
