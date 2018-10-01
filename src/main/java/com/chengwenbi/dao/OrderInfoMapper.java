package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import com.chengwenbi.domain.vo.DataVO;
import com.chengwenbi.domain.vo.ExcelData;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper extends BaseInterfaceMapper<AssetOrderDO> {

    List<OrderItemDO> findOrderItems(OrderItemDO orderItemDO);

    List<AssetOrderDO> findOrders(AssetOrderDO assetOrderDO);

    List<AssetOrderDO> findMaintainOrders(AssetOrderDO assetOrderDO);

    List<DataVO> findOrderByTime(OrderInfoDTO orderInfoDTO);

    List<ExcelData> findExcelData(OrderInfoDTO orderInfoDTO);
}

