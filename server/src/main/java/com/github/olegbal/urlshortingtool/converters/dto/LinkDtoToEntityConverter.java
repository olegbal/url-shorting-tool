package com.github.olegbal.urlshortingtool.converters.dto;

import com.github.olegbal.urlshortingtool.converters.SetConverter;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import org.springframework.core.convert.converter.Converter;

public class LinkDtoToEntityConverter extends SetConverter<LinkDto, Link> implements Converter<LinkDto, Link> {


    @Override
    public Link convert(LinkDto linkDto) {

        Link link = new Link();

        link.setLinkId(linkDto.getLinkId());
        link.setClicksCount(linkDto.getClicksCount());
        link.setOriginalLink(linkDto.getOriginalLink());
        link.setShortLink(linkDto.getShortLink());
        link.setTags(linkDto.getTags());

        return link;
    }
}
