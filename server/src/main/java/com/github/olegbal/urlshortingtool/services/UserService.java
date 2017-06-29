package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    //FIXME return domain model instead of DTO
    UserDto getUserByLogin(String login);

    boolean createUser(RegistrationDto registrationDto);

    Set<UserDto> getRegisteredUsers();

    boolean deleteUser(long id);
}
