package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.converters.dto.LinkDtoToEntityConverter;
import com.github.olegbal.urlshortingtool.converters.entity.LinkEntityToDtoConverter;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.LinkService;
import com.github.olegbal.urlshortingtool.services.UserService;
import com.google.common.collect.Sets;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.CollectionFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Link findByShortLink(String shortLink) {

        Link link = linkRepository.findByShortLink(shortLink);

        if (link != null) {
            return link;
        }

        return null;
    }

    @Override
    public LinkDto getLinkInfoByShortLink(String shortLink) {

        Link link = findByShortLink(shortLink);

        if (link != null) {
            return new LinkEntityToDtoConverter().convert(link);
        }
        return null;
    }


    @Override
    public String checkLink(String shortlink) {

        Link link = findByShortLink(shortlink);

        if (link != null) {
            link.setClicksCount(link.getClicksCount() + 1);
            try {
                linkRepository.save(link);
                return link.getOriginalLink();
            } catch (TransactionException ex) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Set<LinkDto> findAllLinks() {
        Set<Link> links = Sets.newHashSet(linkRepository.findAll());

        if (links != null) {
            return new LinkEntityToDtoConverter().convertSet(links);
        }
        return null;
    }

    @Override
    public boolean updateLink(long userId, LinkDto linkDto) {

        Link link = new LinkDtoToEntityConverter().convert(linkDto);
        User user = userRepository.findOne(userId);

        link.setUser(user);

        user.getLinkSet().add(link);

        try {
            userRepository.save(user);
            return true;
        } catch (TransactionException ex) {

            return false;
        }
    }

    @Override
    public Set<LinkDto> findAllUsersLinks(long userId) {

        Set<Link> links = linkRepository.findByUserUserId(userId);

        if (links != null) {
            return new LinkEntityToDtoConverter().convertSet(links);
        }
        return null;
    }

    @Override
    public boolean removeLink(long id) {

        try {
            linkRepository.delete(id);
            return true;
        } catch (TransactionException ex) {
            return false;
        }
    }

    @Override
    public Set<LinkDto> getLinksByTag(String tag) {

        Set<Link> links = Sets.newHashSet(linkRepository.findAll());

        links = links.stream().filter(x -> x.getTags().contains(tag)).collect(Collectors.toSet());

        return new LinkEntityToDtoConverter().convertSet(links);
    }
}