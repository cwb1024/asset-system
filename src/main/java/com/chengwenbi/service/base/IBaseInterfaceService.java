package com.chengwenbi.service.base;

import com.chengwenbi.common.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础接口
 */
public interface IBaseInterfaceService<Entity extends BaseEntity> {

    public String add(Entity entity) throws Exception;

    public String modify(Entity entity) throws Exception;

    public String delete(Entity entity) throws Exception;

    public Entity findById(String id) throws Exception;

    public List<Entity> findAll() throws Exception;

    public List<Entity> findByMap(Map<String, Object> map) throws Exception;

    public List<Entity> findByParams(Object object) throws Exception;
}
