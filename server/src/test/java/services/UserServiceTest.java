package services;


import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.domain.dto.RegistrationDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.RoleRepository;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.impl.RoleServiceImpl;
import com.github.olegbal.urlshortingtool.services.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest(classes = {Application.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private RoleRepository roleRepository;

    @After
    public void clearRepo() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    public void loadUserByUserNameShouldReturnUser() throws Exception {

        userRepository.save(new User("user1", "1234", null, null));

        User user = userServiceImpl.loadUserByUsername("user1");

        assertThat(user).isNotNull();

        assertThat(user.getLogin()).isEqualTo("user1");
    }

    @Test
    public void getUserByLoginShouldReturnUserDto() throws Exception {

        userRepository.save(new User("user1", "1234", null, null));

        UserDto user = userServiceImpl.getUserByLogin("user1");

        assertThat(user).isNotNull();

        assertThat(user.getLogin()).isEqualTo("user1");
    }

    @Test
    public void createUserShouldAddUserInRepo() throws Exception {
        Role role1 = new Role("ROLE_ADMIN", new HashSet<>());
        Role role2 = new Role("ROLE_USER", new HashSet<>());
        roleRepository.save(role1);
        roleRepository.save(role2);

        assertThat(userServiceImpl.createUser(new RegistrationDto("user1","1234",""))).isTrue();

        assertThat(userServiceImpl.createUser(new RegistrationDto("user1","1234",""))).isFalse();

    }

    @Test
    public void getRegisteredUserShouldReturnUserSet() throws Exception {

        Role role1 = new Role("ROLE_ADMIN", null);
        Role role2 = new Role("ROLE_USER", null);
        roleRepository.save(role1);
        roleRepository.save(role2);
        User user1 = new User("user1", "1234", new HashSet<>(), new HashSet<>());
        User user2 = new User("user2", "1234", new HashSet<>(), new HashSet<>());
        userRepository.save(user1);
        userRepository.save(user2);
        User user3 = userRepository.findByLogin("user1");
        User user4 = userRepository.findByLogin("user2");

        Role role3 = roleRepository.findByRoleName("ROLE_USER");
        Role role4 = roleRepository.findByRoleName("ROLE_ADMIN");

        user3.getRoles().add(role3);
        user4.getRoles().add(role4);
        role3.getUsers().add(user3);
        role4.getUsers().add(user4);
        roleRepository.save(role3);
        userRepository.save(user3);
        roleRepository.save(role4);
        userRepository.save(user4);


        Set<UserDto> registeredUsers = userServiceImpl.getRegisteredUsers();

        assertThat(registeredUsers).isNotNull();
        assertThat(registeredUsers).size().isEqualTo(1);

    }


    @Test
    public void deleteUserShouldDeleteUserFromRepo() throws Exception {
        userRepository.save(new User("user1", "1234", null, null));

        User user = userRepository.findByLogin("user1");

        assertThat(user).isNotNull();

        assertThat(user.getLogin()).isEqualTo("user1");

        userServiceImpl.deleteUser(user.getUserId());

        assertThat(userRepository.findOne(user.getUserId())).isNull();

    }

}
