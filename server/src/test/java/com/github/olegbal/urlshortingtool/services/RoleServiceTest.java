package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.domain.Role;
import com.github.olegbal.urlshortingtool.enums.RolesEnum;
import com.github.olegbal.urlshortingtool.repositories.RoleRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest(classes = {Application.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleServiceTest {

    @Autowired
    private CustomRoleService customRoleService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void getByRoleNameShouldReturnRole() throws Exception {

        roleRepository.save(new Role(RolesEnum.USER.role_name, null));

        Role role = customRoleService.getByRoleName(RolesEnum.USER.role_name);

        assertThat(role).isNotNull();

        assertThat(role.getRoleName()).isEqualTo(RolesEnum.USER.role_name);

    }

    @After
    public void clearRepo() {
        roleRepository.deleteAll();
    }

}
