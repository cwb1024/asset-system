package com.chengwenbi.service.impl;

import com.chengwenbi.constant.AssetStatus;
import com.chengwenbi.dao.AssetMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.service.AssetService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import com.chengwenbi.util.ValidParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AssetServiceImpl extends BaseInterfaceServiceImpl<AssetInfoDO> implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public BaseInterfaceMapper<AssetInfoDO> getBaseInterfaceMapper() {
        return assetMapper;
    }

    @Override
    public String acarp(AssetDTO assetDTO) throws Exception {
        ValidParamUtil.validNotNull(assetDTO.getId());
        AssetInfoDO infoDO = assetMapper.findById(assetDTO.getId());
        infoDO.setStatus(AssetStatus.BREAKDOWN);
        assetMapper.update(infoDO);
        return assetDTO.getId();
    }

    @Override
    public List<AssetInfoDO> findAsset(AssetInfoDO assetInfoDO) {
        return assetMapper.findAsset(assetInfoDO);
    }

}
