package com.chengwenbi.service.impl;

import com.chengwenbi.dao.CategoryMapper;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.CategoryDO;
import com.chengwenbi.service.CategoryService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends BaseInterfaceServiceImpl<CategoryDO> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseInterfaceMapper<CategoryDO> getBaseInterfaceMapper() {
        return categoryMapper;
    }
}
