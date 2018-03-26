package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.common.exception.ValidateParamsException;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.constant.StateConstants;
import com.chengwenbi.constant.StatusConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.AssetDTO;
import com.chengwenbi.domain.entity.AssetInfoDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.AssetService;
import com.chengwenbi.util.PutUserInfoUtil;
import com.chengwenbi.util.StringUtil;
import com.chengwenbi.util.ValidParamUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/asset")
public class AssetInfoController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AssetInfoController.class);

    @Autowired
    private AssetService assetService;

    @ResponseBody
    @RequestMapping("/add")
    public Result addAsset(HttpSession session, AssetDTO assetDTO) {
        UserDO userDO= (UserDO) session.getAttribute(SessionConstants.USER_KEY);
        try {
            String id = StringUtil.uuid();
            AssetInfoDO assetDO = new AssetInfoDO();
            BeanUtils.copyProperties(assetDO, assetDTO);
            PutUserInfoUtil.userInfo(assetDO,userDO);
            assetDO.setId(id);
            assetService.add(assetDO);
        } catch (Exception e) {
            log.error("添加资产失败  " , e);
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
            ValidParamUtil.validNotNull(assetDTO.getId());
            //DTO  转 DO
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            BeanUtils.copyProperties(assetInfoDO,assetDTO);
            PutUserInfoUtil.userInfo(assetInfoDO,userDO);
            assetInfoDO.setStatus(StatusConstants.NORMAL);
            assetService.modify(assetInfoDO);
            result.modifyResult(true,assetDTO.getId(),"修改资产信息成功");
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
            ValidParamUtil.validNotNull(assetDTO.getId());
            AssetInfoDO assetInfoDO = new AssetInfoDO();
            PutUserInfoUtil.userInfo(assetInfoDO,userDO);
            assetInfoDO.setStatus(StatusConstants.DELETED);
            assetService.modify(assetInfoDO);
            result.modifyResult(true,assetDTO.getId(),"资产删除成功");
        }catch (Exception e){
            log.error("资产删除失败",e);
            result.modifyResult(false,assetDTO.getId(),"资产删除失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/find")
    public Result findAsset(){

        return result;
    }
}
