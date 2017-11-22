package com.github.olegbal.urlshortingtool.converters.dto;

import com.github.olegbal.urlshortingtool.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.Link;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class LinkDtoToEntityConverter implements Converter<LinkDto, Link> {

    @Override
    public Link convert(LinkDto linkDto) {
        Link link = new Link();
        link.setLinkId(linkDto.getLinkId());
        link.setClicksCount(linkDto.getClicksCount());
        link.setOriginalLink(linkDto.getOriginalLink());
        link.setShortLink(linkDto.getShortLink());
        link.setTags(linkDto.getTags());
        link.setSummary(linkDto.getSummary());
        link.setCreationDate(linkDto.getCreationDate());
        return link;
    }
}
