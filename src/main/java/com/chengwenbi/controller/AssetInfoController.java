package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/asset")
public class AssetInfoController extends BaseController {

    @ResponseBody
    @RequestMapping("/addAsset")
    public Result addAsset(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/modifyAsset")
    public Result modifyAsset(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteAsset")
    public Result deleteAsset(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/findAsset")
    public Result findAsset(){
        return result;
    }
}
