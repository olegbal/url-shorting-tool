package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.Role;
import com.github.olegbal.urlshortingtool.domain.User;
import com.github.olegbal.urlshortingtool.enums.RolesEnum;
import com.github.olegbal.urlshortingtool.repositories.UserRepository;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Set;

@Service
public class CustomUserService implements UserService {


    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ConversionService conversionService;

    @Value("${admin.serialnumber}")
    private String serialNumber;

    @Autowired
    public CustomUserService(UserRepository userRepository, RoleService roleService, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.conversionService = conversionService;
    }

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
            return conversionService.convert(user, UserDto.class);

        return null;
    }

    @Override

    //FIXME use javax validation OR ->
    public boolean createUser(@Valid RegistrationDto registrationDto) {
//        Optional <RegistrationDto> dto = Optional.ofNullable(registrationDto)
//                .map(dt -> {
//                    validate(dt);
//                    return dt
//                }).

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
                role = roleService.getByRoleName(RolesEnum.USER.role_name);
            } else if (registrationDto.getSerialNumber().equals(serialNumber)) {
                role = roleService.getByRoleName(RolesEnum.ADMIN.role_name);
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
            Set<User> users = roleService.getByRoleName(RolesEnum.USER.role_name).getUsers();

            return (Set<UserDto>) conversionService.convert(users, TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(User.class)),
                    TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(UserDto.class)));
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

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findOne(id);
        if (user != null) {
            return conversionService.convert(user, UserDto.class);
        }

        return null;
    }
}
