package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.dto.RoleDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserEntityToDtoConverter implements Converter<User, UserDto> {

    private final ConversionService conversionService;

    @Autowired
    public UserEntityToDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    @Override
    public UserDto convert(User user) {

        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(((Set<RoleDto>) conversionService.convert(userDto.getLinks(),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Role.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(RoleDto.class)))));
        userDto.setLinks(((Set<LinkDto>) conversionService.convert(userDto.getLinks(),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Link.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(LinkDto.class)))));
        return userDto;
    }
}
