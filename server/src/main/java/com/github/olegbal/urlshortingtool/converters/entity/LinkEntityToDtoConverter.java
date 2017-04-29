package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.converters.SetConverter;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import org.springframework.core.convert.converter.Converter;

public class LinkEntityToDtoConverter extends SetConverter<Link, LinkDto> implements Converter<Link, LinkDto> {

    @Override
    public LinkDto convert(Link link) {

        LinkDto linkDto = new LinkDto();

        linkDto.setLinkId(link.getLinkId());
        linkDto.setOriginalLink(link.getOriginalLink());
        linkDto.setShortLink(link.getShortLink());
        linkDto.setClicksCount(link.getClicksCount());
        linkDto.setTags(link.getTags());
        linkDto.setSummary(link.getSummary());

        return linkDto;
    }
}
