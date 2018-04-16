package com.chengwenbi.service.impl;

import com.chengwenbi.dao.OrderInfoMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import com.chengwenbi.service.OrderInfoService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl extends BaseInterfaceServiceImpl<AssetOrderDO> implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public BaseInterfaceMapper<AssetOrderDO> getBaseInterfaceMapper() {
        return orderInfoMapper;
    }

    @Override
    public List<OrderItemDO> findOrderItems(OrderItemDO orderItemDO) throws Exception {
        return orderInfoMapper.findOrderItems(orderItemDO);
    }

    @Override
    public List<AssetOrderDO> findOrders(AssetOrderDO assetOrderDO) {
        return orderInfoMapper.findOrders(assetOrderDO);
    }

    @Override
    public List<AssetOrderDO> findMaintainOrders(AssetOrderDO assetOrderDO) throws Exception {
        return orderInfoMapper.findMaintainOrders(assetOrderDO);
    }

}
