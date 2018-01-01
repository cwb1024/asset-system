package com.chengwenbi.dao.base;

import java.util.List;
import java.util.Map;

/**
 * mapper 接口层
 */
public interface BaseInterfaceMapper<Entity> {

    /**
     * 保存方法
     */
    public int save(Entity entity);

    /**
     * 修改方法
     */
    public int update(Entity entity);

    /**
     * 删除方法
     */
    public int delete(Entity entity);

    /**
     * 根据ID查询
     */
    public Entity findById(String id);

    /**
     * 查询所有
     */
    public List<Entity> findAll();

    /**
     * 根据条件进行查询
     */
    public List<Entity> findByMap(Map<String, Object> map);


    /**
     * 根据参数查询
     */
    public List<Entity> findByParams(Object object);

}
