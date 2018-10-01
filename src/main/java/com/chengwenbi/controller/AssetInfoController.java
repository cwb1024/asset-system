package com.chengwenbi.controller;

import com.chengwenbi.common.PageBean;
import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.common.exception.ValidateParamsException;
import com.chengwenbi.constant.AssetStatus;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.constant.StateConstants;
import com.chengwenbi.constant.StatusConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.dto.AssetOperDTO;
import com.chengwenbi.domain.dto.OrderInfoDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.CategoryDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.AssetService;
import com.chengwenbi.service.CategoryService;
import com.chengwenbi.util.PutUserInfoUtil;
import com.chengwenbi.util.StringUtil;
import com.chengwenbi.util.ValidParamUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/asset")
public class AssetInfoController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AssetInfoController.class);

    @Autowired
    private AssetService assetService;

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/add")
    public Result addAsset(HttpSession session, AssetDTO assetDTO) {
        UserDO userDO= (UserDO) session.getAttribute(SessionConstants.USER_KEY);
        try {
            if (!"2".equals(userDO.getIdentityId())) {
                throw new ServiceException("非超级管理员不允许操作");
            }
            ValidParamUtil.validNotNull(assetDTO.getName());
            AssetInfoDO queryDO = new AssetInfoDO();
            queryDO.setName(assetDTO.getName());
            List<AssetInfoDO> params = assetService.findByParams(queryDO);
            if (params.size() > 0) {
                throw new ServiceException("这种类型的资产已存在，你可以直接编辑");
            }
            AssetInfoDO assetDO = new AssetInfoDO();
            CategoryDO categoryDO = categoryService.findById(assetDTO.getCategoryId());
            BeanUtils.copyProperties(assetDO, assetDTO);
            assetDO.setCategoryName(categoryDO.getName());
            PutUserInfoUtil.userInfo(assetDO, userDO);
            assetDO.setStatus(AssetStatus.LEISURE);
            String id = assetService.add(assetDO);
            result.modifyResult(true, id, "资产入库成功");
        } catch (ServiceException e) {
            log.error("添加资产失败  ", e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("添加资产失败  ", e);
            result.modifyResult(false, "资产入库失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Result modifyAsset(HttpSession session, AssetDTO assetDTO) {
        //资产id
        UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
        try {
            if (!"2".equals(userDO.getIdentityId())) {
                throw new ServiceException("非超级管理员不允许操作");
            }
            ValidParamUtil.validNotNull(assetDTO.getId());
            //DTO  转 DO
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            BeanUtils.copyProperties(assetInfoDO,assetDTO);
            assetInfoDO.setModifyId(userDO.getId());
            assetInfoDO.setModifyName(userDO.getName());
            assetInfoDO.setModifyTime(new Date());
            assetService.modify(assetInfoDO);
            result.modifyResult(true,assetDTO.getId(),"修改资产信息成功");
        } catch (ServiceException e) {
            log.error("修改资产失败  ", e);
            result.modifyResult(false, e.getMessage());
        } catch (Exception e) {
            log.error("修改资产信息失败", e);
            result.modifyResult(false,"修改资产信息失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Result deleteAsset(HttpSession session,AssetDTO assetDTO){
        UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
        try {
            if (!"2".equals(userDO.getIdentityId())) {
                throw new ServiceException("非超级管理员不允许操作");
            }
            ValidParamUtil.validNotNull(assetDTO.getId());
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            PutUserInfoUtil.userInfo(assetInfoDO,userDO);
            assetInfoDO.setId(assetDTO.getId());
            assetService.delete(assetInfoDO);
            result.modifyResult(true,assetDTO.getId(),"资产删除成功");
        } catch (ServiceException e) {
            log.error("删除资产失败  ", e);
            result.modifyResult(false, e.getMessage());
        }catch (Exception e){
            log.error("资产删除失败",e);
            result.modifyResult(false,assetDTO.getId(),"资产删除失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/find")
    public Result findAsset(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                            AssetDTO assetDTO) {
        try {
            //DTO  转 DO
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            BeanUtils.copyProperties(assetInfoDO, assetDTO);
            //分页查询
            PageHelper.startPage(pageNo, pageSize);

            List<AssetInfoDO> infoDOList = assetService.findByParams(assetInfoDO);

            PageBean<AssetInfoDO> pageBean = new PageBean<>(infoDOList);
            //封装分页数据
            Map<String, Object> map = new HashMap<>();
            map.put("data", infoDOList);
            map.put("total", pageBean.getTotal());
            map.put("pageSize", pageBean.getPageSize());
            map.put("pageNo", pageBean.getPageNum());
            map.put("page", pageBean.getPages());
            result.modifyResult(true, map, "获取资产类表成功");
        } catch (Exception e) {
            log.error("查询资产列表失败", e);
            result.modifyResult(false, "查询资产列表失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findAssetByParams(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                            AssetDTO assetDTO) {
        try {
            //DTO  转 DO
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            BeanUtils.copyProperties(assetInfoDO, assetDTO);
            //分页查询
            PageHelper.startPage(pageNo, pageSize);
            List<AssetInfoDO> infoDOList = assetService.findAsset(assetInfoDO);
            PageBean<AssetInfoDO> pageBean = new PageBean<>(infoDOList);
            //封装分页数据
            Map<String, Object> map = new HashMap<>();
            map.put("data", infoDOList);
            map.put("total", pageBean.getTotal());
            map.put("pageSize", pageBean.getPageSize());
            map.put("pageNo", pageBean.getPageNum());
            map.put("page", pageBean.getPages());
            result.modifyResult(true, map, "获取资产类表成功");
        } catch (Exception e) {
            log.error("查询资产列表失败", e);
            result.modifyResult(false, "查询资产列表失败");
        }
        return result;
    }

    /**
     * 资产报废
     */
    @ResponseBody
    @RequestMapping("/scrap")
    public Result scrapAsset(HttpSession session, AssetDTO assetDTO) {
        try {
            UserDO userDO = (UserDO) session.getAttribute(SessionConstants.USER_KEY);
            PutUserInfoUtil.userInfo(assetDTO, userDO);
            String assetId = assetService.acarp(assetDTO);
            result.modifyResult(true, assetDTO.getName(), "报废成功");
        } catch (Exception e) {
            log.error("报废失败", e);
            result.modifyResult(false, "报废失败");
        }
        return result;
    }

}
