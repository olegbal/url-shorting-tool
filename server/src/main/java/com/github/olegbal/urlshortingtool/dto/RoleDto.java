package com.github.olegbal.urlshortingtool.dto;

public class RoleDto {

    private long roleId;

    private String roleName;

    public RoleDto() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
