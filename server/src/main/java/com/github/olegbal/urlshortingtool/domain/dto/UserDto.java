package com.github.olegbal.urlshortingtool.domain.dto;

import java.util.Set;

public class UserDto {

    private long userId;

    private String login;

    private String password;

    private Set<RoleDto> roles;

    private Set<LinkDto> links;

    public UserDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Set<LinkDto> getLinks() {
        return links;
    }

    public void setLinks(Set<LinkDto> links) {
        this.links = links;
    }
}
