package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.dto.CreatedLinkResponseDto;
import com.github.olegbal.urlshortingtool.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.Link;
import com.github.olegbal.urlshortingtool.domain.User;
import com.github.olegbal.urlshortingtool.repositories.LinkRepository;
import com.github.olegbal.urlshortingtool.repositories.UserRepository;
import com.github.olegbal.urlshortingtool.utils.UrlShortener;
import com.github.olegbal.urlshortingtool.utils.validators.LinkValidator;
import com.google.common.collect.Sets;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomLinkService implements LinkService {

    private final LinkRepository linkRepository;

    private final UserRepository userRepository;

    private final UrlShortener urlShortener;

    private final LinkValidator linkValidator;

    private final ConversionService conversionService;


    @Autowired
    public CustomLinkService(LinkRepository linkRepository,
                             UserRepository userRepository,
                             UrlShortener urlShortener,
                             LinkValidator linkValidator,
                             ConversionService conversionService) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.urlShortener = urlShortener;
        this.linkValidator = linkValidator;
        this.conversionService = conversionService;
    }

    @Override
    public LinkDto getLinkById(long id) {
            return conversionService.convert(linkRepository.findOne(id),LinkDto.class);
    }

    @Override
    public Link findByShortLink(String shortLink) {
        //FIXME add finals
        Link link = linkRepository.findByShortLink(shortLink);

        if (link != null) {
            return link;
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

        return (Set<LinkDto>)conversionService.convert(links, TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Link.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(LinkDto.class)));
    }

    @Override
    public CreatedLinkResponseDto createLink(long userId, LinkDto linkDto) {

        if (linkDto.getTags() != null && linkDto.getSummary() != null && linkDto.getOriginalLink() != null
                && linkDto.getCreationDate() != null && linkValidator.validate(linkDto.getOriginalLink())) {

            Link link = conversionService.convert(linkDto, Link.class);

            User user = userRepository.findOne(userId);

            link.setUser(user);
            link.setShortLink(urlShortener.shortUrl(linkDto.getOriginalLink()));
            user.getLinkSet().add(link);

            try {
                userRepository.save(user);

                long id = linkRepository.findByShortLink(link.getShortLink()).getLinkId();
                return new CreatedLinkResponseDto(id, link.getShortLink());
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean updateLink(long userId, LinkDto linkDto) {

        User user = null;

        if (linkDto.getTags() != null && linkDto.getSummary() != null && linkDto.getOriginalLink() != null
                && linkDto.getShortLink() != null && linkDto.getCreationDate() != null) {

            user = userRepository.findOne(userId);
            if (user != null) {
                Link editingLink = user.getLinkSet().stream().filter(x -> x.getLinkId() == linkDto.getLinkId()).findAny().get();

                editingLink.setSummary(linkDto.getSummary());
                editingLink.setTags(linkDto.getTags());
            }

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
    public Set<LinkDto> findAllUsersLinks(Pageable pageable, long userId) {

        Set<Link> links = Sets.newHashSet(linkRepository.findByUserUserId(pageable, userId));

        if (links != null) {
            return (Set<LinkDto>) conversionService.convert(links, TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Link.class)),
                    TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(LinkDto.class)));
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

        return (Set<LinkDto>) conversionService.convert(links, TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Link.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(LinkDto.class)));
    }

    @Override
    public LinkDto getByOriginalLink(String originalLink) {

        try {
            Link link = linkRepository.findByOriginalLink(originalLink);
            return conversionService.convert(link, LinkDto.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
