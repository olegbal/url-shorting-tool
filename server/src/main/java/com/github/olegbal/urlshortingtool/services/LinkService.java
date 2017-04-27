package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;

import java.util.Set;

public interface LinkService {

    Link findByShortLink(String shortLink);

    Set<LinkDto> findAllLinks();

    boolean updateLink(long userId, LinkDto linkDto);

    Set<LinkDto> findAllUsersLinks(long userId);

    boolean removeLink(long link);

    String checkLink(String shortlink);

    LinkDto getLinkInfoByShortLink(String shortLink);

    Set<LinkDto> getLinksByTag(String tag);

}
