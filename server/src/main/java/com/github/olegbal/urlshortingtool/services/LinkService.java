package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.dto.CreatedLinkResponseDto;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Set;

public interface LinkService {

    Link findByShortLink(String shortLink);

    Set<LinkDto> findAllLinks(Pageable pageable);

    CreatedLinkResponseDto createLink(long userId, LinkDto linkDto);

    Set<LinkDto> findAllUsersLinks(Pageable pageable, long userId);

    boolean removeLink(long link);

    String checkLink(String shortlink);

    Set<LinkDto> getLinksByTag(Pageable pageable, String tag);

    LinkDto getLinkById(long id);

    boolean updateLink(long userId, LinkDto linkDto);

}
