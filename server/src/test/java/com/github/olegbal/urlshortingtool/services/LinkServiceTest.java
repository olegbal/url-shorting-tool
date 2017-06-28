package com.github.olegbal.urlshortingtool.services;


import com.github.olegbal.urlshortingtool.Application;
import com.github.olegbal.urlshortingtool.converters.entity.LinkEntityToDtoConverter;
import com.github.olegbal.urlshortingtool.domain.dto.CreatedLinkResponseDto;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.impl.CustomLinkService;
import com.github.olegbal.urlshortingtool.services.impl.CustomUserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@SpringBootTest(classes = {Application.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LinkServiceTest {

    @Autowired
    private CustomLinkService customLinkService;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private UserRepository userRepository;

    @After
    public void clearRepo() {
        linkRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getLinkByIdShouldReturnLink() throws Exception {
        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));
        Link link = linkRepository.findByShortLink("test_short_link");
        assertThat(link).isNotNull();
        LinkDto link1 = customLinkService.getLinkById(link.getLinkId());
        assertThat(link1).isNotNull();
        assertThat(link1.getLinkId() == link.getLinkId());
    }

    @Test
    public void findByOriginalLinkShouldReturnLink() throws Exception {

        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));
        Link link = linkRepository.findByOriginalLink("test_original_link");
        assertThat(link).isNotNull();
        LinkDto link1 = customLinkService.getByOriginalLink(link.getOriginalLink());
        assertThat(link1).isNotNull();
        assertThat(link1.getOriginalLink()).isEqualTo("test_original_link");
    }


    @Test
    public void findByShortLinkShouldReturnLink() throws Exception {

        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));
        Link link = linkRepository.findByShortLink("test_short_link");
        assertThat(link).isNotNull();
        Link link1 = customLinkService.findByShortLink(link.getShortLink());
        assertThat(link1).isNotNull();
        assertThat(link1.getShortLink()).isEqualTo("test_short_link");

    }

    @Test
    public void checkLinkShouldReturnOriginalLinkAndIncrementClickCount() throws Exception {
        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));

        Link link = linkRepository.findByShortLink("test_short_link");
        assertThat(link).isNotNull();

        String expectedOriginalLInk = customLinkService.checkLink(link.getShortLink());

        assertThat(expectedOriginalLInk).isNotNull();

        assertThat(expectedOriginalLInk).isEqualTo("test_original_link");

        link = linkRepository.findByOriginalLink("test_original_link");

        assertThat(link).isNotNull();

        assertThat(link.getClicksCount()).isEqualTo(1);

    }

    @Test
    public void findAllLinksShouldReturnPagedLinkSet() throws Exception {

        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));

        linkRepository.save(new Link("test_original_link2",
                "test_short_link2", 0, "tag3 tag4"
                , "summary2", null, null));
        Set<LinkDto> linkDtoSet = customLinkService.findAllLinks(new PageRequest(0, 2));
        assertThat(linkDtoSet).isNotNull();
        assertThat(linkDtoSet).size().isEqualTo(2);
        linkDtoSet = customLinkService.findAllLinks(new PageRequest(1, 1));
        assertThat(linkDtoSet).isNotNull();
        assertThat(linkDtoSet).size().isEqualTo(1);

    }

    @Test
    public void createLinkShouldReturnCreatedLinkResponseDto() throws Exception {
//
        userRepository.save(new User("test_user_1", "1234", null, null));

        User user = userRepository.findByLogin("test_user_1");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("test_user_1");

        CreatedLinkResponseDto createdLinkResponseDto = customLinkService.createLink(user.getUserId(), new LinkDto(0,
                "test.originallink.com", "test_short_link",
                0, "tag1 tag2", "summary 1", new Date()));

        assertThat(createdLinkResponseDto).isNotNull();

        Link link = linkRepository.findOne(createdLinkResponseDto.getLinkId());

        assertThat(link).isNotNull();
        assertThat(link.getOriginalLink()).isEqualTo("test.originallink.com");

        assertThat(link.getUser().getUsername()).isEqualTo("test_user_1");

        assertThat(customLinkService.createLink(user.getUserId(), new LinkDto(1,
                "test.originallink.com", "test_short_link",
                0, "tag1 tag2", "summary 1", null))).isNull();


    }

    @Test
    public void updateLinkShouldChangeLinkInRepo() throws Exception {
        userRepository.save(new User("test_user_1", "1234", null, null));

        User user = userRepository.findByLogin("test_user_1");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("test_user_1");

        CreatedLinkResponseDto createdLinkResponseDto = customLinkService.createLink(user.getUserId(), new LinkDto(0,
                "test.originallink.com", "test_short_link",
                0, "tag1 tag2", "summary 1", new Date()));

        assertThat(createdLinkResponseDto).isNotNull();

        Link link = linkRepository.findOne(createdLinkResponseDto.getLinkId());

        assertThat(link).isNotNull();
        assertThat(link.getOriginalLink()).isEqualTo("test.originallink.com");

        link.setTags("tag1 tag2 tag3");
        link.setSummary("summary2");

        assertThat(customLinkService.updateLink(user.getUserId(), new LinkEntityToDtoConverter().convert(link))).isTrue();

        link = linkRepository.findOne(createdLinkResponseDto.getLinkId());

        assertThat(link).isNotNull();

        assertThat(link.getTags()).isEqualTo("tag1 tag2 tag3");
        assertThat(link.getSummary()).isEqualTo("summary2");

    }

    @Test
    public void findAllUsersLinksShouldReturnLinkSet() throws Exception {

        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));
        userRepository.save(new User("test_user_1", "1234", null, null));

    }

    @Test
    public void findLinksByTagsShouldReturnLinkSet() throws Exception {
        linkRepository.save(new Link("test_original_link1",
                "test_short_link1", 0, "tag1 tag2"
                , "summary1", null, null));
        linkRepository.save(new Link("test_original_link2",
                "test_short_link2", 0, "tag2 tag3"
                , "summary2", null, null));
        Set<LinkDto> linkDtoSet = customLinkService.getLinksByTag(new PageRequest(0, 2), "tag2");
        assertThat(linkDtoSet).size().isEqualTo(2);

        linkDtoSet = customLinkService.getLinksByTag(new PageRequest(0, 2), "tag3");
        assertThat(linkDtoSet).size().isEqualTo(1);

    }

    @Test
    public void removeLinkShouldDeleteLinkFromRepo() throws Exception {

        linkRepository.save(new Link("test_original_link",
                "test_short_link", 0, "tag1 tag2"
                , "summary1", null, null));
        Link link = linkRepository.findByOriginalLink("test_original_link");
        assertThat(link).isNotNull();
        assertThat(customLinkService.removeLink(link.getLinkId())).isTrue();
        assertThat(linkRepository.findOne(link.getLinkId())).isNull();
    }


}
