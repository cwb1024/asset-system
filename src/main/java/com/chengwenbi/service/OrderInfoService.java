package com.chengwenbi.service;

import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import com.chengwenbi.service.base.IBaseInterfaceService;

import java.util.List;

public interface OrderInfoService extends IBaseInterfaceService<AssetOrderDO> {
    List<OrderItemDO> findOrderItems(OrderItemDO orderItemDO)throws Exception;

    List<AssetOrderDO> findOrders(AssetOrderDO assetOrderDO) throws Exception;

    List<AssetOrderDO> findMaintainOrders(AssetOrderDO assetOrderDO) throws Exception;

}
