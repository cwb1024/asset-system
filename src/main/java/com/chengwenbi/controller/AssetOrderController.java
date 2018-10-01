package com.chengwenbi.controller;

import com.chengwenbi.common.PageBean;
import com.chengwenbi.common.Result;
import com.chengwenbi.config.ConfigMap;
import com.chengwenbi.constant.OrderStatus;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.AssetOrderDO;
import com.chengwenbi.domain.entity.OrderItemDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.domain.vo.DataVO;
import com.chengwenbi.domain.vo.ExcelData;
import com.chengwenbi.service.OrderInfoService;
import com.chengwenbi.util.DateUtil;
import com.chengwenbi.util.ExcelParam;
import com.chengwenbi.util.ExcelUtil;
import com.chengwenbi.util.ValidParamUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/api/order")
public class AssetOrderController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(AssetInfoController.class);

    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 获取订单列表
     */
    @RequestMapping("/findByPage")
    @ResponseBody
    public Result findOrderListByPage(HttpSession session, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      OrderInfoDTO orderInfoDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            AssetOrderDO assetOrderDO = new AssetOrderDO();
            BeanUtils.copyProperties(assetOrderDO, orderInfoDTO);
            PageHelper.startPage(pageNo, pageSize);
            if ("1".equals(userDO.getIdentityId())) {
                assetOrderDO.setCreateId(userDO.getId());
            }
            List<AssetOrderDO> orderDOS = orderInfoService.findByParams(assetOrderDO);
            PageBean<AssetOrderDO> pageBean = new PageBean<>(orderDOS);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("data", orderDOS);
            map.put("total", pageBean.getTotal());
            map.put("pageSize", pageBean.getPageSize());
            map.put("pageNo", pageBean.getPageNum());
            map.put("page", pageBean.getPages());
            result.modifyResult(true, map, "查询订单列表成功");
        } catch (Exception e) {
            log.error("订单列表查询失败", e);
            result.modifyResult(false, "订单列表查询失败");
        }
        return result;
    }

    @RequestMapping("/findOrderItem")
    @ResponseBody
    public Result findOrderItem(OrderItemDO orderItemDO,@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderItemDO> orderItems = null;
        try {
            orderItems = orderInfoService.findOrderItems(orderItemDO);
            result.modifyResult(true,orderItems,"获取清单详情成功");
        } catch (Exception e) {
            log.error("获取失败", e);
            result.modifyResult(false, orderItemDO.getId(), "获取失败");
        }
        return result;
    }

    @RequestMapping("/findApprover")
    @ResponseBody
    public Result findApproveOrder(HttpSession session, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      OrderInfoDTO orderInfoDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            AssetOrderDO assetOrderDO = new AssetOrderDO();
            BeanUtils.copyProperties(assetOrderDO, orderInfoDTO);
            PageHelper.startPage(pageNo, pageSize);
            assetOrderDO.setPropApproverId(userDO.getId());//预批准人
            assetOrderDO.setStatus(OrderStatus.DAIAPPROVAL);
            List<AssetOrderDO> orderDOS = orderInfoService.findByParams(assetOrderDO);
            PageBean<AssetOrderDO> pageBean = new PageBean<>(orderDOS);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("data", orderDOS);
            map.put("total", pageBean.getTotal());
            map.put("pageSize", pageBean.getPageSize());
            map.put("pageNo", pageBean.getPageNum());
            map.put("page", pageBean.getPages());
            result.modifyResult(true, map, "查询订单列表成功");
        } catch (Exception e) {
            log.error("订单列表查询失败", e);
            result.modifyResult(false, "订单列表查询失败");
        }
        return result;
    }


    /*待维护资产数据查询*/
    @ResponseBody
    @RequestMapping("/findMaintainOrders")
    public Result findMaintainOrders(OrderInfoDTO orderInfoDTO,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        AssetOrderDO assetOrderDO = new AssetOrderDO();
        try {
            BeanUtils.copyProperties(assetOrderDO, orderInfoDTO);
            PageHelper.startPage(pageNo, pageSize);
            List<AssetOrderDO> maintainOrders = orderInfoService.findMaintainOrders(assetOrderDO);
            PageBean<AssetOrderDO> pageBean = new PageBean<>(maintainOrders);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("data", maintainOrders);
            map.put("total", pageBean.getTotal());
            map.put("pageSize", pageBean.getPageSize());
            map.put("pageNo", pageBean.getPageNum());
            map.put("page", pageBean.getPages());
            result.modifyResult(true, map, "查询订单列表成功");
        } catch (Exception e) {
            log.error("订单列表查询失败", e);
            result.modifyResult(false, "订单列表查询失败");
        }
        return result;
    }
    /**
     * 根据日期范围查询订单详情
     */
    @RequestMapping("/findOrderByDate")
    @ResponseBody
    public Result findOrderByDate(OrderInfoDTO orderInfoDTO) {
        //统计这个日期范围的
        try {
            List<DataVO> maps = orderInfoService.findOrderByTime(orderInfoDTO);
            result.modifyResult(true, maps,"获取统计数据成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Excel 数据导出，根据选择日范围导出   借出 、
     */
    @RequestMapping(value = "/download")
    public void download(OrderInfoDTO orderInfoDTO,HttpServletResponse response) throws Exception {

        List<ExcelData> excelData = orderInfoService.findExcelData(orderInfoDTO);
        String[] heads = {"操作类型", "清单编号", "资产名称", "类别名称", "数量","借用人","审批人","审批时间"};
        List<String[]> data = new LinkedList<>();
        for (int i = 0; i < excelData.size(); i++) {
            ExcelData entity = excelData.get(i);
            String[] temp = new String[8];
            String typeName = "";
            if (entity.getType() == 5) {
                typeName = "归还";
            } else if (entity.getType() == 7) {
                typeName = "借出";
            } else {
                typeName = "报废";
            }
            temp[0] = typeName;
            temp[1] = entity.getOrderId();
            temp[2] = entity.getName();
            temp[3] = entity.getCategoryName();
            temp[4] = String.valueOf(entity.getAccount());
            temp[5] = entity.getCreateName();
            temp[6] = entity.getApproverName();

            temp[7] = DateUtil.formatDate(entity.getCreateTime());
            data.add(temp);
        }
        ExcelParam param =new ExcelParam.Builder("数据统计").headers(heads).data(data).build();
        ExcelUtil.export(param,response);
    }
}
