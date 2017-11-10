package com.github.olegbal.urlshortingtool.enums;

public enum DbTableNames {
    USERS_TABLE("users"), ROLES_TABLE("roles"), LINKS_TABLE("user_links"), STATISTICS_TABLE("statistics"), USER_ROLES_TABLE("user_roles");

    private String tableName;

    DbTableNames(String tableName) {
        this.tableName = tableName;
    };

    @Override
    public String toString(){
        return tableName;
    }
}
