package com.chengwenbi.domain.entity;
/**
 * @description:资产类别关系表实体
 * @author: chengwenbi
 * @date:   2018/3/13 17:01
 */
public class AssetCategoryDO {

    private String id;
    private String assetId;
    private String assetCategoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetCategoryId() {
        return assetCategoryId;
    }

    public void setAssetCategoryId(String assetCategoryId) {
        this.assetCategoryId = assetCategoryId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AssetCategoryDO [");
        if (id != null) {
            builder.append("id=").append(id).append(",");
        }
        if (assetId != null) {
            builder.append("assetId=").append(assetId).append(",");
        }
        if (assetCategoryId != null) {
            builder.append("assetCategoryId=").append(assetCategoryId);
        }
        builder.append("]");
        return builder.toString();
    }
}
