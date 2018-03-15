package com.chengwenbi.service.impl;

import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.domain.entity.CategoryDO;
import com.chengwenbi.service.CategoryService;
import com.chengwenbi.service.base.BaseInterfaceServiceImpl;

public class CategoryServiceImpl extends BaseInterfaceServiceImpl<CategoryDO> implements CategoryService {
    @Override
    public BaseInterfaceMapper<CategoryDO> getBaseInterfaceMapper() {
        return null;
    }
}
