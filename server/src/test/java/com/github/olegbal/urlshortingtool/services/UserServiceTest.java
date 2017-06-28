package com.github.olegbal.urlshortingtool.services;


import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.domain.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.enums.RolesEnum;
import com.github.olegbal.urlshortingtool.respositories.RoleRepository;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.impl.CustomRoleService;
import com.github.olegbal.urlshortingtool.services.impl.CustomUserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest(classes = {Application.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomRoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @After
    public void clearRepo() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void loadUserByUserNameShouldReturnUser() throws Exception {

        userRepository.save(new User("test_user_1", "1234", null, null));

        User user = customUserService.loadUserByUsername("test_user_1");

        assertThat(user).isNotNull();

        assertThat(user.getLogin()).isEqualTo("test_user_1");
    }

    @Test
    public void getUserByLoginShouldReturnUserDto() throws Exception {

        userRepository.save(new User("test_user_1", "1234", null, null));

        UserDto user = customUserService.getUserByLogin("test_user_1");

        assertThat(user).isNotNull();

        assertThat(user.getLogin()).isEqualTo("test_user_1");
    }

    @Test
    public void createUserShouldAddUserInRepo() throws Exception {
        Role role1 = new Role(RolesEnum.ADMIN.role_name, new HashSet<>());
        Role role2 = new Role(RolesEnum.USER.role_name, new HashSet<>());
        roleRepository.save(role1);
        roleRepository.save(role2);

        assertThat(customUserService.createUser(new RegistrationDto("test_user_1", "1234", ""))).isTrue();

        assertThat(customUserService.createUser(new RegistrationDto("test_user_1", "1234", ""))).isFalse();

    }

    @Test
    public void getRegisteredUserShouldReturnUserSet() throws Exception {

        Role role1 = new Role(RolesEnum.ADMIN.role_name, null);
        Role role2 = new Role(RolesEnum.USER.role_name, null);
        roleRepository.save(role1);
        roleRepository.save(role2);
        User user1 = new User("test_user_1", "1234", new HashSet<>(), new HashSet<>());
        User user2 = new User("test_user_2", "1234", new HashSet<>(), new HashSet<>());
        userRepository.save(user1);
        userRepository.save(user2);
        User user3 = userRepository.findByLogin("test_user_1");
        User user4 = userRepository.findByLogin("test_user_2");

        Role role3 = roleRepository.findByRoleName(RolesEnum.USER.role_name);
        Role role4 = roleRepository.findByRoleName(RolesEnum.ADMIN.role_name);

        user3.getRoles().add(role3);
        user4.getRoles().add(role4);
        role3.getUsers().add(user3);
        role4.getUsers().add(user4);
        roleRepository.save(role3);
        userRepository.save(user3);
        roleRepository.save(role4);
        userRepository.save(user4);


        Set<UserDto> registeredUsers = customUserService.getRegisteredUsers();

        assertThat(registeredUsers).isNotNull();
        assertThat(registeredUsers).size().isEqualTo(1);

    }


    @Test
    public void deleteUserShouldDeleteUserFromRepo() throws Exception {
        userRepository.save(new User("test_user_1", "1234", null, null));

        User user = userRepository.findByLogin("test_user_1");

        assertThat(user).isNotNull();

        assertThat(user.getLogin()).isEqualTo("test_user_1");

        customUserService.deleteUser(user.getUserId());

        assertThat(userRepository.findOne(user.getUserId())).isNull();

    }

}
