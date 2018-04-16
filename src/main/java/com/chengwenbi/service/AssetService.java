package com.chengwenbi.service;

import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.service.base.IBaseInterfaceService;

import java.util.List;

public interface AssetService extends IBaseInterfaceService<AssetInfoDO>{

    String acarp(AssetDTO assetDTO) throws Exception;

    List<AssetInfoDO> findAsset(AssetInfoDO assetInfoDO);
}
