package com.chengwenbi.dao;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOperDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderOperMapper extends BaseInterfaceMapper<AssetOperDO> {

    public int insertList(@Param("list") List<OrderItemDO> itemDOList);
}
