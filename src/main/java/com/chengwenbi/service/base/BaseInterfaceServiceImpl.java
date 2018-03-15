package com.chengwenbi.service.base;

import com.chengwenbi.common.BaseEntity;
import com.chengwenbi.dao.base.BaseInterfaceMapper;
import com.chengwenbi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor=Exception.class)
public abstract class BaseInterfaceServiceImpl<Entity extends BaseEntity> implements IBaseInterfaceService<Entity> {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String add(Entity entity) throws Exception {
        String id = StringUtil.uuid();
        entity.setId(id);
        this.getBaseInterfaceMapper().save(entity);
        return id;
    }

    @Override
    public String modify(Entity entity) throws Exception {
        String id = entity.getId();
        this.getBaseInterfaceMapper().update(entity);
        return id;
    }

    @Override
    public String delete(Entity entity) throws Exception {
        String id = entity.getId();
        this.getBaseInterfaceMapper().delete(entity);
        return id;
    }

    @Override
    public Entity findById(String id) throws Exception {
        Entity entity = this.getBaseInterfaceMapper().findById(id);
        return entity;
    }

    @Override
    public List<Entity> findAll() throws Exception {
        List<Entity> list = this.getBaseInterfaceMapper().findAll();
        return list;
    }

    @Override
    public List<Entity> findByMap(Map<String, Object> map) throws Exception {
        List<Entity> list = this.getBaseInterfaceMapper().findByMap(map);
        return list;
    }

    @Override
    public List<Entity> findByParams(Object object) throws Exception {
        List<Entity> list = this.getBaseInterfaceMapper().findByParams(object);
        return list;
    }

    /**
     * 抽象方法需要实现,得到基础服务接口
     * @return
     */
    public abstract BaseInterfaceMapper<Entity> getBaseInterfaceMapper();
}
