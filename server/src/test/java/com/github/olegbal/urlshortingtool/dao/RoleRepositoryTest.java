package com.github.olegbal.urlshortingtool.dao;

import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.respositories.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest(classes = {Application.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void findByRolenameShouldReturnRole() throws Exception{
        this.entityManager.persist(new Role("ROLE_USER",null));

        Role role = roleRepository.findByRoleName("ROLE_USER");

        assertThat(role.getRoleName()).isEqualTo("ROLE_USER");
    }

}
