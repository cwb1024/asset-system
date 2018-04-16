package com.chengwenbi.controller;

import com.alibaba.fastjson.JSONException;
import com.chengwenbi.common.Result;

import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.dto.AssetOperDTO;
import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOperDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.AssetService;
import com.chengwenbi.service.OrderOperService;
import com.chengwenbi.util.PutUserInfoUtil;
import com.chengwenbi.util.ValidParamUtil;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/oper")
public class AssetOperController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AssetOperController.class);


    @Autowired
    private AssetService assetService;

    @Autowired
    private OrderOperService orderOperService;

    /**
     * 资产申请借出
     */
    @ResponseBody
    @RequestMapping("/apply")
    public Result applyAsset(AssetDTO assetDTO, HttpSession session){
        String assetName = assetDTO.getName();
        try {
            ValidParamUtil.validNotNull(assetName);
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            assetInfoDO.setName(assetName);
            //1.判断资产是否可以借出
            List<AssetInfoDO> assetInfoDOS = assetService.findByParams(assetInfoDO);
            if (assetInfoDOS.size() == 0) {
                throw new ServiceException("当前没有这种类型的资产");
            }
            Integer account = assetDTO.getAccount();
            if (account > assetInfoDOS.get(0).getAccount()) {
                throw new ServiceException("库存只有"+assetInfoDOS.size()+"个");
            }
            //如果正常，那么开始创建一个申请单
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            PutUserInfoUtil.userInfo(assetDTO,userDO);
            String orderId = orderOperService.createOrder(assetDTO,assetInfoDOS.get(0).getAccount());
            result.modifyResult(true,orderId, "申请单填写成功");
        } catch (Exception e) {
            log.error("申请单填写失败  " , e);
            result.modifyResult(false, "申请单填写失败");
        }
        return result;
    }

    /**
     * 资产借出批准
     */
    @ResponseBody
    @RequestMapping("/approve")
    public Result approveAsset(HttpSession session, OrderInfoDTO orderInfoDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            Date now = new Date();
            orderInfoDTO.setNowUserId(userDO.getId());
            orderInfoDTO.setNowUserName(userDO.getName());
            orderInfoDTO.setNowTime(now);
            orderInfoDTO.setApproverId(userDO.getId());
            orderInfoDTO.setApproverName(userDO.getName());
            orderInfoDTO.setApproveTime(now);
            String orderId = orderOperService.approveOrder(orderInfoDTO);
            result.modifyResult(true,orderId,"审批成功");
        } catch (Exception e) {
            log.error("审批失败", e);
            result.modifyResult(false, "审批失败");
        }
        return result;
    }

    /**
     * 否决资产借出批准
     */
    @ResponseBody
    @RequestMapping("/disApprove")
    public Result disApproveAsset(HttpSession session, OrderInfoDTO orderInfoDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            Date now = new Date();
            orderInfoDTO.setNowUserId(userDO.getId());
            orderInfoDTO.setNowUserName(userDO.getName());
            orderInfoDTO.setNowTime(now);
            orderInfoDTO.setApproverId(userDO.getId());
            orderInfoDTO.setApproverName(userDO.getName());
            orderInfoDTO.setApproveTime(now);
            String orderId = orderOperService.disApproveOrder(orderInfoDTO);
            result.modifyResult(true,orderId,"否决成功");
        } catch (Exception e) {
            log.error("否决失败", e);
            result.modifyResult(false, "否决失败");
        }
        return result;
    }

    /**
     * 资产归还
     */
    @ResponseBody
    @RequestMapping("/repay")
    public Result repayAsset(HttpSession session, AssetOperDTO assetOperDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            PutUserInfoUtil.userInfo(assetOperDTO, userDO);
            orderOperService.repayOrder(assetOperDTO);
            result.modifyResult(true, assetOperDTO.getOrderId(), "归还成功");
        } catch (Exception e) {
            log.error("归还失败", e);
            result.modifyResult(false, "归还失败");
        }
        return result;
    }

    /**
     * 资产报废
     */
    @ResponseBody
    @RequestMapping("/scrap")
    public Result scrapAsset(HttpSession session, AssetOperDTO assetOperDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            PutUserInfoUtil.userInfo(assetOperDTO, userDO);
            orderOperService.acrapOrder(assetOperDTO);
            result.modifyResult(true, assetOperDTO.getOrderId(), "报废成功");
        } catch (Exception e) {
            log.error("报废失败", e);
            result.modifyResult(false, "报废失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findFlow")
    public Result findFlow(HttpSession session, AssetOperDTO assetOperDTO) {
        try {
            ValidParamUtil.validNotNull(assetOperDTO.getOrderId());
            List<AssetOperDO> flow = orderOperService.findFlow(assetOperDTO);
            result.modifyResult(true, flow, "获取流程成功");
        } catch (Exception e) {
            log.error("查询流程失败", e);
            result.modifyResult(false, "报废失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/startOrder")
    public Result createOrder(HttpSession session, AssetOperDTO assetOperDTO) {
        UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
        try {
            PutUserInfoUtil.userInfo(assetOperDTO, userDO);
            String id = orderOperService.startOrder(assetOperDTO);
            result.modifyResult(true, id, "创建清单成功");
        } catch (JSONException e) {
            result.modifyResult(false,"数据为空，操作失败");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /*资产领用，维护一个数量*/
    @ResponseBody
    @RequestMapping("/receive")
    public Result receiveOrder(HttpSession session, AssetOperDTO assetOperDTO) {
        //当前用户身份是资产管理员
        UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
        try {
            PutUserInfoUtil.userInfo(assetOperDTO, userDO);
            String id = orderOperService.receiverOrder(assetOperDTO);
            result.modifyResult(true, id, "资产领取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
