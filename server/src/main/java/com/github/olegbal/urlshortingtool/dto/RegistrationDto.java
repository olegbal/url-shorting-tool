package com.github.olegbal.urlshortingtool.dto;

import javax.validation.constraints.NotNull;

public class RegistrationDto {

    private String login;

    private String password;

    private String serialNumber;

    public RegistrationDto() {
    }

    public RegistrationDto(String login, String password, String serialNumber) {
        this.login = login;
        this.password = password;
        this.serialNumber = serialNumber;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
