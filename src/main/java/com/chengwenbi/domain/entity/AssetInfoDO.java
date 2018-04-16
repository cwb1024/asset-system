package com.chengwenbi.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.chengwenbi.common.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:资产详情表实体
 * @author: chengwenbi
 * @date:   2018/3/13 17:02
 */
public class AssetInfoDO extends BaseEntity {

    private String name;
    private String assetNo;//资产编号
    private String categoryId;//类别id
    private String categoryName;//资产名字
    private BigDecimal money;

    /*@JSONField(format = "yyyy-MM-dd hh:mm:ss")*/
    private Date purchaseTime;//购买时间
    private Integer status;//资产状态
    private Integer account;//总量
    private String producers;

    @JSONField(format = "yyyy-MM-dd")
    private Date produceDate;

    //借出数量
    private Integer applyCount;

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }
}
