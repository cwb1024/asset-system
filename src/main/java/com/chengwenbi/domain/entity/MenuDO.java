package com.chengwenbi.domain.entity;

/**
 * @description:菜单表实体
 * @author: chengwenbi
 * @date:   2018/3/13 17:04
 */
public class MenuDO {
    private String id;
    private String name;
    private String parentId;
    private Integer degree;
    private Integer sort;
    private Integer isShow;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenuDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", degree=" + degree +
                ", sort=" + sort +
                ", isShow=" + isShow +
                '}';
    }
}
