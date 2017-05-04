package com.github.olegbal.urlshortingtool.dao;

import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest(classes = {Application.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LinkRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LinkRepository repository;

    @Test
    public void findByOriginalLinkShouldReturnLink() throws Exception {
        this.entityManager.persist(new Link("test_origLink",
                "test_shortlink", 0, "tag1 tag2",
                "summary 1", new Date(), null));
        Link link = repository.findByOriginalLink("test_origLink");
        assertThat(link.getOriginalLink()).isEqualTo("test_origLink");
    }

    @Test
    public void findByShortLinkShouldReturnLink() throws Exception {
        this.entityManager.persist(new Link("test_origLink",
                "test_shortlink", 0, "tag1 tag2",
                "summary 1", new Date(), null));
        Link link = repository.findByShortLink("test_shortlink");
        assertThat(link.getShortLink()).isEqualTo("test_shortlink");
    }

    @Test
    public void findByUserIdShouldReturnLinkPage() throws Exception {

        User user = new User("test_user_1", "test_user_1", new HashSet<Link>(), null);

        user.getLinkSet().add(new Link("test_origLink",
                "test_shortlink", 0, "tag1 tag2",
                "summary 1", new Date(), user));
        user.getLinkSet().add(new Link("test_link_2",
                "test_shortlink_2", 0, "tag3 tag4",
                "summary 2", new Date(), user));
        this.entityManager.persist(user);


        assertThat(this.repository.findByUserUserId(new PageRequest(0, 1),
                (long) this.entityManager.getId(user)).getSize())
                .isEqualTo(1);

        assertThat(this.repository.findByUserUserId(new PageRequest(0, 2),
                (long) this.entityManager.getId(user)).getSize())
                .isEqualTo(2);

        assertThat(this.repository.findByUserUserId(new PageRequest(1, 1),
                (long) this.entityManager.getId(user)).getSize())
                .isEqualTo(1);


    }

    @Test
    public void removeLinksByCreationDateBeforeShouldDeleteExpiredLinks() throws Exception {

        this.entityManager.persist(new Link("test_origLink",
                "test_shortlink", 0, "tag1 tag2",
                "summary 1", new Date(System.currentTimeMillis()), null));

        this.entityManager.persist(new Link("test_link_2",
                "test_shortlink_2", 0, "tag3 tag4",
                "summary 2", new Date(System.currentTimeMillis() + 2000), null));

        repository.removeLinksByCreationDateBefore(new Date(System.currentTimeMillis() + 1000));

        assertThat(repository.findByOriginalLink("test_origLink")).isNull();

        assertThat(repository.findByOriginalLink("test_link_2")).isNotNull();
    }


}