package dao;

import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        this.entityManager.persist(new Link("http://github.com",
                "Abcdef", 0, "tag1 tag2",
                "summary 1", new Date(), null));
        Link link = repository.findByOriginalLink("http://github.com");
        assertThat(link.getOriginalLink()).isEqualTo("http://github.com");
    }

    @Test
    public void findByShortLinkShouldReturnLink() throws Exception {
        this.entityManager.persist(new Link("http://github.com",
                "Abcdef", 0, "tag1 tag2",
                "summary 1", new Date(), null));
        Link link = repository.findByShortLink("Abcdef");
        assertThat(link.getShortLink()).isEqualTo("Abcdef");
    }

    @Test
    public void findByUserIdShouldReturnLinkPage() throws Exception {


    }

    @Test
    public void removeLinksByCreationDateBeforeShouldDeleteExpiredLinks() throws Exception {

        this.entityManager.persist(new Link("http://github.com",
                "Abcdef", 0, "tag1 tag2",
                "summary 1", new Date(System.currentTimeMillis()), null));

        this.entityManager.persist(new Link("http://link2.com",
                "Ghijkl", 0, "tag3 tag4",
                "summary 2", new Date(System.currentTimeMillis() + 2000), null));

        repository.removeLinksByCreationDateBefore(new Date(System.currentTimeMillis() + 1000));

        assertThat(repository.findByOriginalLink("http://github.com")).isNull();

        assertThat(repository.findByOriginalLink("http://link2.com")).isNotNull();
    }


}