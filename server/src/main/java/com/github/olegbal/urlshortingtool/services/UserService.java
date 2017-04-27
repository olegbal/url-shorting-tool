package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.dto.LoginAndPasswordDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;

public interface UserService {

    UserDto getUserByLogin(String login);

    boolean createUser(LoginAndPasswordDto loginAndPasswordDto);
}
