package com.chengwenbi.controller;

import com.chengwenbi.common.Result;
import com.chengwenbi.common.exception.ServiceException;
import com.chengwenbi.constant.SessionConstants;
import com.chengwenbi.controller.base.BaseController;
import com.chengwenbi.domain.dto.CategoryDTO;
import com.chengwenbi.domain.dto.UserDTO;
import com.chengwenbi.domain.entity.CategoryDO;
import com.chengwenbi.domain.entity.UserDO;
import com.chengwenbi.service.CategoryService;
import com.chengwenbi.util.StringUtil;
import com.chengwenbi.util.ValidParamUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/add")
    public Result addCategory(CategoryDTO categoryDTO) {
        try {
            ValidParamUtil.validNotNull(categoryDTO.getName());
            String id = StringUtil.uuid();
            CategoryDO categoryDO = new CategoryDO();
            categoryDO.setId(id);
            categoryDO.setName(categoryDTO.getName());
            categoryDO.setParentId(categoryDTO.getParentId());
            categoryService.add(categoryDO);
        } catch (ServiceException e) {
            log.warn("添加类别警告异常", e);
            result.modifyResult(false,"添加类别警告异常");
        } catch (Exception e) {
            log.error("添加类别失败",e);
            result.modifyResult(false,"添加类别失败");
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Result modifyCategory(CategoryDTO categoryDTO) {
        try {
            String id = categoryDTO.getId();
            ValidParamUtil.validNotNull(id);
            CategoryDO categoryDO = categoryService.findById(id);
            if (categoryDO == null) {
                throw new ServiceException("当前类别不存在");
            }
            //更新操作
            categoryDO.setName(categoryDTO.getName());
            categoryDO.setParentId(categoryDTO.getParentId());
            categoryService.modify(categoryDO);
            result. modifyResult(true, "更新类别成功");
        } catch (ServiceException e) {
            log.warn("更新类别异常", e);
            result.modifyResult(false,"更新类别异常");
        }catch (Exception e){
            log.error("更新类别失败", e);
            result.modifyResult(false,"更新类别失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Result deleteCategory(CategoryDTO categoryDTO) {
        try {
            String id = categoryDTO.getId();
            ValidParamUtil.validNotNull(id);
            CategoryDO categoryDO = new CategoryDO();
            BeanUtils.copyProperties(categoryDO, categoryDTO);
            categoryService.delete(categoryDO);
            result. modifyResult(true, "删除类别成功");
        } catch (ServiceException e) {
            log.warn("删除类别异常", e);
            result.modifyResult(false,"删除类别异常");
        }catch (Exception e){
            log.error("删除类别失败", e);
            result.modifyResult(false,"删除类别失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findAll")
    public Result findCategory(){
        try {
            List<CategoryDO> doList = categoryService.findAll();
            result.modifyResult(true,doList,"获取资产类别成功");
        } catch (ServiceException e) {
            log.warn("查询类别异常", e);
            result.modifyResult(false,"查询类别异常");
        }catch (Exception e){
            log.error("查询类别失败", e);
            result.modifyResult(false,"查询类别失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Result findOne(CategoryDTO categoryDTO) {
        try {
            ValidParamUtil.validNotNull(categoryDTO.getId());
            CategoryDO categoryDO = categoryService.findById(categoryDTO.getId());
            result.modifyResult(true,categoryDO,"获取类别成功");
        } catch (ServiceException e) {
            log.warn("查询类别异常", e);
            result.modifyResult(false,"查询类别异常");
        }catch (Exception e){
            log.error("查询类别失败", e);
            result.modifyResult(false,"查询类别失败");
        }
        return result;
    }
}
