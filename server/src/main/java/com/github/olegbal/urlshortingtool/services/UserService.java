package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;

import java.util.Set;

public interface UserService {

    UserDto getUserByLogin(String login);

    boolean createUser(RegistrationDto registrationDto);

    Set<UserDto> getRegisteredUsers();

    boolean deleteUser(long id);
}
