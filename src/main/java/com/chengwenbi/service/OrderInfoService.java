package com.chengwenbi.service;

import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import com.chengwenbi.domain.vo.DataVO;
import com.chengwenbi.domain.vo.ExcelData;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import com.chengwenbi.service.base.IBaseInterfaceService;
import com.chengwenbi.util.ExcelUtil;

import java.util.List;
import java.util.Map;

public interface OrderInfoService extends IBaseInterfaceService<AssetOrderDO> {
    List<OrderItemDO> findOrderItems(OrderItemDO orderItemDO)throws Exception;

    List<AssetOrderDO> findOrders(AssetOrderDO assetOrderDO) throws Exception;

    List<AssetOrderDO> findMaintainOrders(AssetOrderDO assetOrderDO) throws Exception;

    List<DataVO> findOrderByTime(OrderInfoDTO orderInfoDTO) throws Exception;

    List<ExcelData> findExcelData(OrderInfoDTO orderInfoDTO) throws Exception;

}
