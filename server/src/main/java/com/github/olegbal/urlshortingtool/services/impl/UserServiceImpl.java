package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.converters.entity.UserEntityToDtoConverter;
import com.github.olegbal.urlshortingtool.domain.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.UserService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Value("${admin.serialnumber}")
    private String serialNumber;

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
    public boolean createUser(RegistrationDto registrationDto) {

        if (registrationDto.getSerialNumber() != null
                && registrationDto.getLogin() != null
                && registrationDto.getPassword() != null
                && !registrationDto.getPassword().equals("")
                && !registrationDto.getLogin().equals("")) {


            User user = userRepository.findByLogin(registrationDto.getLogin());
            if (user != null) {
                return false;
            }
            user = new User();
            user.setLogin(registrationDto.getLogin());
            user.setPassword(registrationDto.getPassword());

            Role role = null;

            if (registrationDto.getSerialNumber().equals("")) {
                role = roleService.getByRoleName("ROLE_USER");
            } else if (registrationDto.getSerialNumber().equals(serialNumber)) {
                role = roleService.getByRoleName("ROLE_ADMIN");
            } else if (!registrationDto.getSerialNumber().equals(serialNumber)) {
                return false;
            }


            user.getRoles().add(role);

            try {
                userRepository.save(user);
                return true;
            } catch (TransactionException ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Set<UserDto> getRegisteredUsers() {

        try {
            Set<User> users = roleService.getByRoleName("ROLE_USER").getUsers();

            return new UserEntityToDtoConverter().convertSet(users);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean deleteUser(long id) {

        try {
            userRepository.delete(id);
            return true;
        } catch (TransactionException ex) {
            return false;
        }
    }
}
