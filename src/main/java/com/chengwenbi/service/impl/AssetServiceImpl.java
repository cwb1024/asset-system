package com.chengwenbi.service.impl;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.service.AssetService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;

public class AssetServiceImpl extends BaseInterfaceServiceImpl<AssetInfoDO> implements AssetService {
    @Override
    public BaseInterfaceMapper<AssetInfoDO> getBaseInterfaceMapper() {
        return null;
    }
}
