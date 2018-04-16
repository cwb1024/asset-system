package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;

import java.util.List;

public interface OrderInfoMapper extends BaseInterfaceMapper<AssetOrderDO> {

    List<OrderItemDO> findOrderItems(OrderItemDO orderItemDO);

    List<AssetOrderDO> findOrders(AssetOrderDO assetOrderDO);

    List<AssetOrderDO> findMaintainOrders(AssetOrderDO assetOrderDO);
}
