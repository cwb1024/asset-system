package com.chengwenbi.domain;

import com.chengwenbi.common.BaseEntity;

public class UserDO extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private Integer identity;
    private Integer state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDO [");
        if (getId() != null) {
            builder.append("id=").append(getId()).append(",");
        }
        if (name != null) {
            builder.append("name=").append(name).append(",");
        }
        if (email != null) {
            builder.append("email=").append(email).append(",");
        }
        if (password != null) {
            builder.append("password=").append(password).append(",");
        }
        if (identity != null) {
            builder.append("identity=").append(identity).append(",");
        }
        if (state != null) {
            builder.append("state=").append(state).append(",");
        }
        if (getCreateId() != null) {
            builder.append("createId=").append(getCreateId()).append(",");
        }
        if (getCreateName() != null) {
            builder.append("createName=").append(getCreateName()).append(",");
        }
        if (getCreateTime() != null) {
            builder.append("createTime=").append(getCreateTime()).append(",");
        }
        if (getModifyId() != null) {
            builder.append("modifyId=").append(getModifyId()).append(",");
        }
        if (getModifyName() != null) {
            builder.append("modifyName=").append(getModifyName()).append(",");
        }
        if (getModifyTime() != null) {
            builder.append("modifyTime=").append(getModifyTime());
        }
        builder.append("]");
        return builder.toString();
    }

}
