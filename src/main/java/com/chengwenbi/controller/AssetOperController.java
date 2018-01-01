package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class AssetOperController extends BaseController {

    /**
     * 资产申请借出
     */
    @ResponseBody
    @RequestMapping("/applyAsset")
    public Result applyAsset(){
        return result;
    }

    /**
     * 资产借出批准
     */
    @ResponseBody
    @RequestMapping("/approveAsset")
    public Result approveAsset(){
        return result;
    }

    /**
     * 资产归还
     */
    @ResponseBody
    @RequestMapping("/repayAsset")
    public Result repayAsset(){
        return result;
    }

    /**
     * 资产报废
     */
    @ResponseBody
    @RequestMapping("/scrapAsset")
    public Result scrapAsset(){
        return result;
    }
}
