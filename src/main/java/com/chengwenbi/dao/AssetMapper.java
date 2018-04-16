package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.AssetInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AssetMapper extends BaseInterfaceMapper<AssetInfoDO> {

    public Integer updateCount(AssetInfoDO assetInfoDO);


    List<AssetInfoDO> findAsset(AssetInfoDO assetInfoDO);
}
