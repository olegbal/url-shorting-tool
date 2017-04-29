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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(s);

        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public UserDto getUserByLogin(String login) {

        User user;
        user = userRepository.findByLogin(login);

        if (user != null)
            return new UserEntityToDtoConverter().convert(user);

        return null;
    }

    @Override
    public boolean createUser(LoginAndPasswordDto loginAndPasswordDto) {

        User user = new User();
        user.setLogin(loginAndPasswordDto.getLogin());
        user.setPassword(loginAndPasswordDto.getPassword());
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        role.setRoleId(1);
        user.getRoles().add(role);


        try {
            userRepository.save(user);
            return true;
        } catch (TransactionException ex) {
            return false;
        }
    }


}
