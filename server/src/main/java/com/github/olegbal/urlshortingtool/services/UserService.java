package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    UserDto getUserByLogin(String login);

    boolean createUser(RegistrationDto registrationDto);

    Set<UserDto> getRegisteredUsers();

    boolean deleteUser(long id);
}
