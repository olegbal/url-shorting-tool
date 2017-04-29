package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.converters.dto.LinkDtoToEntityConverter;
import com.github.olegbal.urlshortingtool.converters.entity.LinkEntityToDtoConverter;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import com.github.olegbal.urlshortingtool.services.LinkService;
import com.google.common.collect.Sets;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LinkDto getLinkById(long id) {
        Link link = linkRepository.findOne(id);

        if (link != null) {
            return new LinkEntityToDtoConverter().convert(link);
        }
        return null;
    }

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
    public Set<LinkDto> findAllLinks(Pageable pageable) {

        Set<Link> links = new HashSet<>(linkRepository.findAll(pageable).getContent());

        if (links != null) {
            return new LinkEntityToDtoConverter().convertSet(links);
        }
        return null;
    }

    @Override
    public long createLink(long userId, LinkDto linkDto) {

        Link link = new LinkDtoToEntityConverter().convert(linkDto);
        User user = userRepository.findOne(userId);

        link.setUser(user);

        user.getLinkSet().add(link);

        try {
            userRepository.save(user);
            return linkRepository.findByShortLink(link.getShortLink()).getLinkId();
        } catch (TransactionException ex) {
            return 0;
        }
    }

    @Override
    public Set<LinkDto> findAllUsersLinks(Pageable pageable, long userId) {

        Set<Link> links = Sets.newHashSet(linkRepository.findByUserUserId(pageable, userId).getContent());

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
    public Set<LinkDto> getLinksByTag(Pageable pageable, String tag) {

        Set<Link> links = Sets.newHashSet(linkRepository.findAll(pageable).getContent());

        links = links.stream().filter(x -> x.getTags().contains(tag)).collect(Collectors.toSet());

        return new LinkEntityToDtoConverter().convertSet(links);
    }

    @Override
    public boolean updateLink(long userId, LinkDto linkDto) {
        Link link = new LinkDtoToEntityConverter().convert(linkDto);
        User user = userRepository.findOne(userId);

        Link deletingLink = user.getLinkSet().stream().filter(x -> x.getLinkId() == linkDto.getLinkId()).findAny().get();
        user.getLinkSet().remove(deletingLink);
        user.getLinkSet().add(link);
        link.setUser(user);

        user.getLinkSet().add(link);

        try {
            userRepository.save(user);
            return true;
        } catch (TransactionException ex) {

            return false;
        }

    }

}