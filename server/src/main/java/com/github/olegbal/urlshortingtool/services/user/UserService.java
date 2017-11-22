package com.github.olegbal.urlshortingtool.services.user;

import com.github.olegbal.urlshortingtool.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    //FIXME return domain model instead of DTO
    UserDto getUserByLogin(String login);

    UserDto getUserById(Long id);

    boolean createUser(RegistrationDto registrationDto);

    Set<UserDto> getRegisteredUsers();

    boolean deleteUser(long id);
}
