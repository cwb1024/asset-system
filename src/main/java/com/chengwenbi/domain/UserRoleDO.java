package com.chengwenbi.domain;

public class UserRoleDO {

    private String id;
    private String userId;
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserRoleDO [");
        if (id != null) {
            builder.append("id=").append(id).append(",");
        }
        if (userId != null) {
            builder.append("userId=").append(userId).append(",");
        }
        if (roleId != null) {
            builder.append("roleId=").append(roleId);
        }
        builder.append("]");
        return builder.toString();
    }
}
