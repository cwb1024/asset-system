package com.chengwenbi.service;

import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.dto.AssetOperDTO;
import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetOperDO;
import com.chengwenbi.service.base.IBaseInterfaceService;

import java.util.List;

public interface OrderOperService extends IBaseInterfaceService<AssetOperDO> {

    String createOrder(AssetDTO assetDTO,Integer size) throws Exception;

    String approveOrder(OrderInfoDTO orderInfoDTO) throws Exception;

    String repayOrder(AssetOperDTO assetOperDTO) throws Exception;

    String acrapOrder(AssetOperDTO assetOperDTO) throws Exception;

    List<AssetOperDO> findFlow(AssetOperDTO assetOperDTO) throws Exception;

    String startOrder(AssetOperDTO assetOperDTO) throws Exception;

    String disApproveOrder(OrderInfoDTO orderInfoDTO)throws Exception;

    String receiverOrder(AssetOperDTO assetOperDTO) throws Exception;

}
