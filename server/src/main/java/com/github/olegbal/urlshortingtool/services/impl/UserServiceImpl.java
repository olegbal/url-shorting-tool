package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.converters.entity.UserEntityToDtoConverter;
import com.github.olegbal.urlshortingtool.domain.dto.LoginAndPasswordDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.UserService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByLogin(s);
    }

    @Override
    public UserDto getUserByLogin(String login) {

        User user;
        user = userRepository.findByLogin(login);

        return new UserEntityToDtoConverter().convert(user);
    }

    @Override
    public boolean createUser(LoginAndPasswordDto loginAndPasswordDto) {

        User user = new User();
        user.setLogin(loginAndPasswordDto.getLogin());
        user.setPassword(loginAndPasswordDto.getPassword());
        Role role=new Role();
        role.setRoleName("ROLE_USER");
        role.setRoleId(1);
        user.getRoles().add(role);

        try {
            userRepository.save(user);
        } catch (TransactionException ex) {
            return false;
        }
        return true;
    }
}
