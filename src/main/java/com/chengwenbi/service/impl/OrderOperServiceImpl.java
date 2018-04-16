package com.chengwenbi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.constant.AssetStatus;
import com.chengwenbi.constant.OrderOperType;
import com.chengwenbi.constant.OrderStatus;
import com.chengwenbi.dao.AssetMapper;
import com.chengwenbi.dao.OrderInfoMapper;
import com.chengwenbi.dao.OrderOperMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.dto.AssetOperDTO;
import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOperDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import com.chengwenbi.service.OrderOperService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import com.chengwenbi.util.StringUtil;
import com.chengwenbi.util.ValidParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(rollbackFor = Exception.class)
public class OrderOperServiceImpl extends BaseInterfaceServiceImpl<AssetOperDO> implements OrderOperService {

    @Autowired
    private OrderOperMapper orderOperMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private AssetMapper assetMapper;


    //为了父类拿到mapper对象
    @Override
    public BaseInterfaceMapper<AssetOperDO> getBaseInterfaceMapper() {
        return orderOperMapper;
    }


    @Override
    public String createOrder(AssetDTO assetDTO,Integer size)throws Exception{
        //插入订单表
        AssetOrderDO oi = new AssetOrderDO();
        String orderId = StringUtil.uuid();
        oi.setId(orderId);
        oi.setStatus(OrderStatus.DAIAPPROVAL);
        oi.setCreateId(assetDTO.getCreateId());
        oi.setCreateName(assetDTO.getCreateName());
        oi.setCreateTime(assetDTO.getCreateTime());
        oi.setNowUserId(assetDTO.getCreateId());
        oi.setNowUserName(assetDTO.getCreateName());
        oi.setNowTime(assetDTO.getCreateTime());
        orderInfoMapper.save(oi);
        //资产表数量维护
        /*AssetInfoDO assetInfoDO = new AssetInfoDO();
        Integer account = size - assetDTO.getAccount();
        assetInfoDO.setName(assetDTO.getName());
        List<AssetInfoDO> orderDOS = assetMapper.findByParams(assetInfoDO);
        AssetInfoDO infoDO = orderDOS.get(0);
        assetInfoDO.setAccount(account);
        assetInfoDO.setId(infoDO.getId());
        assetMapper.update(assetInfoDO);*/
        //插入订单操作表
        AssetOperDO oper = new AssetOperDO();
        String operId = StringUtil.uuid();
        oper.setId(operId);
        oper.setOrderId(orderId);
        oper.setType(OrderOperType.CREATE_ORDER);
        oper.setBeforeStatus(OrderStatus.BEFORE_CREATE);
        oper.setAfterStatus(OrderStatus.DAIAPPROVAL);
        oper.setCreateId(assetDTO.getCreateId());
        oper.setCreateName(assetDTO.getCreateName());
        oper.setCreateTime(assetDTO.getCreateTime());
        oper.setOperationTime("");
        orderOperMapper.save(oper);
        //给选择的批准人发送邮件提醒
        return orderId;
    }

    @Override
    public String approveOrder(OrderInfoDTO orderInfoDTO)throws Exception {
        String orderId = orderInfoDTO.getId();
        ValidParamUtil.validNotNull(orderId);
        AssetOrderDO orderDO = orderInfoMapper.findById(orderId);
        if (orderDO.getStatus() != OrderStatus.DAIAPPROVAL) {
            throw new ServiceException("非待批准的订单不允许批准");
        }
        //更新订单的状态
        orderDO.setStatus(OrderStatus.APPROVALING);
        orderDO.setApproverId(orderInfoDTO.getApproverId());
        orderDO.setApproverName(orderInfoDTO.getApproverName());
        orderDO.setApproveTime(orderInfoDTO.getApproveTime());
        orderDO.setNowUserId(orderInfoDTO.getNowUserId());
        orderDO.setNowUserName(orderInfoDTO.getNowUserName());
        orderDO.setNowTime(orderInfoDTO.getNowTime());
        orderInfoMapper.update(orderDO);
        //操作记录表
        Date nowTime = orderDO.getNowTime();
        AssetOperDO operDO = new AssetOperDO();
        String operId = StringUtil.uuid();
        operDO.setId(operId);
        operDO.setOrderId(orderInfoDTO.getId());
        operDO.setCreateTime(orderInfoDTO.getApproveTime());
        operDO.setCreateName(orderInfoDTO.getApproverName());
        operDO.setCreateId(orderInfoDTO.getApproverId());
        operDO.setType(OrderOperType.AGREE_APPROVAL);
        operDO.setBeforeStatus(OrderStatus.DAIAPPROVAL);
        operDO.setAfterStatus(OrderStatus.APPROVALING);
        if (nowTime == null) {
            operDO.setOperationTime(String.valueOf(orderInfoDTO.getApproveTime().getTime()));
        } else {
            operDO.setOperationTime(String.valueOf(orderInfoDTO.getApproveTime().getTime() - nowTime.getTime()));
        }
        orderOperMapper.save(operDO);
        return orderInfoDTO.getId();
    }

    @Override
    public String repayOrder(AssetOperDTO assetOperDTO) throws Exception {
        String orderId = assetOperDTO.getOrderId();
        ValidParamUtil.validNotNull(orderId);
        AssetOrderDO orderDO = orderInfoMapper.findById(orderId);
        if (orderDO == null) {
            throw new ServiceException("清单不存在，不允许归还");
        }
        if (orderDO.getStatus() != OrderStatus.HIRE) {
            throw new ServiceException("非借用中的订单不允许归还");
        }
        AssetOrderDO assetOrderDO = new AssetOrderDO();
        assetOrderDO.setId(assetOperDTO.getOrderId());
        assetOrderDO.setNowTime(assetOperDTO.getCreateTime());
        assetOrderDO.setNowUserId(assetOperDTO.getCreateId());
        assetOrderDO.setNowUserName(assetOperDTO.getCreateName());
        assetOrderDO.setStatus(OrderStatus.REPAY);
        //更新订单表
        orderInfoMapper.update(assetOrderDO);
        //更新操作记录表
        AssetOperDO operDO = new AssetOperDO();
        Date nowTime = orderDO.getNowTime();
        String operId = StringUtil.uuid();
        operDO.setId(operId);
        operDO.setOrderId(orderId);
        operDO.setCreateTime(assetOperDTO.getCreateTime());
        operDO.setCreateName(assetOperDTO.getCreateName());
        operDO.setCreateId(assetOperDTO.getCreateId());
        operDO.setType(OrderOperType.REPAY);
        operDO.setBeforeStatus(OrderStatus.HIRE);
        operDO.setAfterStatus(OrderStatus.REPAY);
        operDO.setOperationTime(String.valueOf(assetOperDTO.getCreateTime().getTime() - nowTime.getTime()));
        orderOperMapper.save(operDO);
        //资产数量维护
        OrderItemDO itemDO = new OrderItemDO();
        itemDO.setId(orderId);
        List<OrderItemDO> orderItems = orderInfoMapper.findOrderItems(itemDO);
        for (int i = 0; i < orderItems.size(); i++) {
            AssetInfoDO as = assetMapper.findById(orderItems.get(i).getAssetId());
            String id= orderItems.get(i).getAssetId();
            Integer count= as.getAccount() + orderItems.get(i).getAccount();
            AssetInfoDO infoDO = new AssetInfoDO();
            infoDO.setId(id);
            infoDO.setAccount(count);
            if (count == 0) {
                infoDO.setStatus(AssetStatus.NONE);
            } else if (count > 0) {
                infoDO.setStatus(AssetStatus.LEISURE);
            }
            assetMapper.updateCount(infoDO);
        }
        return orderId;
    }

    @Override
    public String acrapOrder(AssetOperDTO assetOperDTO) throws Exception {
        String orderId = assetOperDTO.getOrderId();
        ValidParamUtil.validNotNull(orderId);
        AssetOrderDO orderDO = orderInfoMapper.findById(orderId);
        AssetOrderDO assetOrderDO = new AssetOrderDO();
        assetOrderDO.setId(assetOperDTO.getOrderId());
        assetOrderDO.setNowTime(assetOperDTO.getCreateTime());
        assetOrderDO.setNowUserId(assetOperDTO.getCreateId());
        assetOrderDO.setNowUserName(assetOperDTO.getCreateName());
        assetOrderDO.setStatus(OrderStatus.DESTROY);
        //更新订单表
        orderInfoMapper.update(assetOrderDO);
        //更新操作记录表
        AssetOperDO operDO = new AssetOperDO();
        Date nowTime = orderDO.getNowTime();
        String operId = StringUtil.uuid();
        operDO.setId(operId);
        operDO.setOrderId(orderId);
        operDO.setCreateTime(assetOperDTO.getCreateTime());
        operDO.setCreateName(assetOperDTO.getCreateName());
        operDO.setCreateId(assetOperDTO.getCreateId());
        operDO.setType(OrderOperType.DESTROY);
        operDO.setBeforeStatus(OrderStatus.HIRE);
        operDO.setAfterStatus(OrderStatus.REPAY);
        operDO.setOperationTime(String.valueOf(assetOperDTO.getCreateTime().getTime() - nowTime.getTime()));
        orderOperMapper.save(operDO);
        //资产表数量维护
        AssetInfoDO assetInfoDO = new AssetInfoDO();
        //数量
       /* Integer account = orderDO.getAccount() + assetOperDTO.getAccount();
        assetInfoDO.setAccount(account);
        //去维护哪一个
        assetInfoDO.setName(orderDO.getName());*/
        List<AssetInfoDO> orderDOS = assetMapper.findByParams(assetInfoDO);
        AssetInfoDO infoDO = orderDOS.get(0);
        /*assetInfoDO.setAccount(account);*/
        assetInfoDO.setId(infoDO.getId());
        assetMapper.update(assetInfoDO);
        return orderId;
    }

    @Override
    public List<AssetOperDO> findFlow(AssetOperDTO assetOperDTO) throws Exception {
        List<AssetOperDO> operDOS = orderOperMapper.findByParams(assetOperDTO);
        return operDOS;
    }

    @Override
    public String startOrder(AssetOperDTO assetOperDTO) throws Exception {
        String orderId = StringUtil.uuid();
        //创建一个清单
        AssetOrderDO oi = new AssetOrderDO();
        oi.setId(orderId);
        oi.setCreateId(assetOperDTO.getCreateId());
        oi.setCreateName(assetOperDTO.getCreateName());
        oi.setCreateTime(assetOperDTO.getCreateTime());
        oi.setNowUserId(assetOperDTO.getCreateId());
        oi.setNowUserName(assetOperDTO.getCreateName());
        oi.setNowTime(assetOperDTO.getCreateTime());
        oi.setStatus(OrderStatus.DAIAPPROVAL);
        oi.setPropApproverId(assetOperDTO.getPropApproverId());
        oi.setPropApproverName(assetOperDTO.getPropApproverName());
        oi.setRemark(assetOperDTO.getRemark());
        orderInfoMapper.save(oi);
        //操作表记录
        AssetOperDO oper= new AssetOperDO();
        String operId = StringUtil.uuid();
        oper.setId(operId);
        oper.setOrderId(orderId);
        oper.setType(OrderOperType.CREATE_ORDER);
        oper.setBeforeStatus(OrderStatus.BEFORE_CREATE);
        oper.setAfterStatus(OrderStatus.DAIAPPROVAL);
        oper.setCreateId(assetOperDTO.getCreateId());
        oper.setCreateName(assetOperDTO.getCreateName());
        oper.setCreateTime(assetOperDTO.getCreateTime());
        oper.setOperationTime("");
        orderOperMapper.save(oper);
        //订单详情记录
        String jsonData = assetOperDTO.getJsonStr();
        List<AssetInfoDO> assetInfoDOS = JSONArray.parseArray(jsonData, AssetInfoDO.class);
        List<OrderItemDO> itemDOS = new ArrayList<>();
        for (int i = 0; i < assetInfoDOS.size(); i++) {
            OrderItemDO itemDO = new OrderItemDO();
            String itemId = StringUtil.uuid();
            itemDO.setId(itemId);
            itemDO.setAssetId(assetInfoDOS.get(i).getId());
            itemDO.setAccount(assetInfoDOS.get(i).getApplyCount());
            itemDO.setCategoryId(assetInfoDOS.get(i).getCategoryId());
            itemDO.setCategoryName(assetInfoDOS.get(i).getCategoryName());
            itemDO.setName(assetInfoDOS.get(i).getName());
            itemDO.setOrderId(orderId);
            itemDOS.add(itemDO);
        }
        //维护订单详情表
        int i = orderOperMapper.insertList(itemDOS);
        if (i == 0) {
            throw new ServiceException("批量插入失败");
        }
        return orderId;
    }

    @Override
    public String disApproveOrder(OrderInfoDTO orderInfoDTO) throws Exception {
        String orderId = orderInfoDTO.getId();
        ValidParamUtil.validNotNull(orderId);
        AssetOrderDO orderDO = orderInfoMapper.findById(orderId);
        if (orderDO.getStatus() != OrderStatus.DAIAPPROVAL) {
            throw new ServiceException("非待批准的订单不允许批准");
        }
        //更新订单的状态
        orderDO.setStatus(OrderStatus.DISAGREE);
        orderDO.setApproverId(orderInfoDTO.getApproverId());
        orderDO.setApproverName(orderInfoDTO.getApproverName());
        orderDO.setApproveTime(orderInfoDTO.getApproveTime());
        orderDO.setNowUserId(orderInfoDTO.getNowUserId());
        orderDO.setNowUserName(orderInfoDTO.getNowUserName());
        orderDO.setNowTime(orderInfoDTO.getNowTime());
        orderInfoMapper.update(orderDO);
        //操作记录表
        Date nowTime = orderDO.getNowTime();
        AssetOperDO operDO = new AssetOperDO();
        String operId = StringUtil.uuid();
        operDO.setId(operId);
        operDO.setOrderId(orderInfoDTO.getId());
        operDO.setCreateTime(orderInfoDTO.getApproveTime());
        operDO.setCreateName(orderInfoDTO.getApproverName());
        operDO.setCreateId(orderInfoDTO.getApproverId());
        operDO.setType(OrderOperType.DISAGREE_APPROVAL);
        operDO.setBeforeStatus(OrderStatus.DAIAPPROVAL);
        operDO.setAfterStatus(OrderStatus.DISAGREE);
        if (nowTime == null) {
            operDO.setOperationTime(String.valueOf(orderInfoDTO.getApproveTime().getTime()));
        } else {
            operDO.setOperationTime(String.valueOf(orderInfoDTO.getApproveTime().getTime() - nowTime.getTime()));
        }
        orderOperMapper.save(operDO);
        return orderId;
    }

    @Override
    public String receiverOrder(AssetOperDTO assetOperDTO) throws Exception {
        String orderId = assetOperDTO.getOrderId();
        ValidParamUtil.validNotNull(orderId);
        AssetOrderDO orderDO = orderInfoMapper.findById(orderId);
        if (orderDO == null) {
            throw new ServiceException("清单不存在");
        }
        orderDO.setStatus(OrderStatus.HIRE);
        orderDO.setNowTime(assetOperDTO.getCreateTime());
        orderDO.setNowUserName(assetOperDTO.getCreateName());
        orderDO.setNowUserId(assetOperDTO.getCreateId());
        orderInfoMapper.update(orderDO);
        //操作表
        AssetOperDO oper= new AssetOperDO();
        String operId = StringUtil.uuid();
        oper.setId(operId);
        oper.setOrderId(orderId);
        oper.setType(OrderOperType.HIRE);
        oper.setBeforeStatus(OrderStatus.APPROVALING);
        oper.setAfterStatus(OrderStatus.HIRE);
        oper.setCreateId(assetOperDTO.getCreateId());
        oper.setCreateName(assetOperDTO.getCreateName());
        oper.setCreateTime(assetOperDTO.getCreateTime());
        oper.setOperationTime("");
        orderOperMapper.save(oper);
        //维护数量
        OrderItemDO itemDO = new OrderItemDO();
        itemDO.setId(orderId);
        List<OrderItemDO> orderItems = orderInfoMapper.findOrderItems(itemDO);
        for (int i = 0; i < orderItems.size(); i++) {
            AssetInfoDO as = assetMapper.findById(orderItems.get(i).getAssetId());
            String id= orderItems.get(i).getAssetId();
            Integer count= as.getAccount() - orderItems.get(i).getAccount();
            AssetInfoDO infoDO = new AssetInfoDO();
            infoDO.setId(id);
            infoDO.setAccount(count);
            if (count == 0) {
                infoDO.setStatus(AssetStatus.NONE);
            }
            assetMapper.updateCount(infoDO);
        }
        return orderId;
    }
}
