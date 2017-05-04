package com.github.olegbal.urlshortingtool.enums;

public enum RolesEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    public String role_name;

    RolesEnum(String s) {
        this.role_name = s;
    }
}