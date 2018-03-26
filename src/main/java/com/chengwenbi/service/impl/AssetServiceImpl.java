package com.chengwenbi.service.impl;

import com.chengwenbi.dao.AssetMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.service.AssetService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl extends BaseInterfaceServiceImpl<AssetInfoDO> implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public BaseInterfaceMapper<AssetInfoDO> getBaseInterfaceMapper() {
        return assetMapper;
    }
}
