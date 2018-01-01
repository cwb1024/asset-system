package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class CategoryController extends BaseController {

    @ResponseBody
    @RequestMapping("/addCategory")
    public Result addCategory(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/modifyCategory")
    public Result modifyCategory(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteCategory")
    public Result deleteCategory(){
        return result;
    }

    @ResponseBody
    @RequestMapping("/findCategory")
    public Result findCategory(){
        return result;
    }
}
