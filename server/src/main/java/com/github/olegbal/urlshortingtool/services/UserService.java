package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.dto.UserDto;

public interface UserService {

    UserDto getUserByLogin(String login);
}
