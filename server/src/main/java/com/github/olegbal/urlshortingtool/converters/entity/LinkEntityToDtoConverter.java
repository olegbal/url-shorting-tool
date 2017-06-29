package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.Link;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class LinkEntityToDtoConverter implements Converter<Link, LinkDto> {

    @Override
    public LinkDto convert(Link link) {
        LinkDto linkDto = new LinkDto();
        linkDto.setLinkId(link.getLinkId());
        linkDto.setOriginalLink(link.getOriginalLink());
        linkDto.setShortLink(link.getShortLink());
        linkDto.setClicksCount(link.getClicksCount());
        linkDto.setTags(link.getTags());
        linkDto.setSummary(link.getSummary());
        linkDto.setCreationDate(link.getCreationDate());
        return linkDto;
    }
}
