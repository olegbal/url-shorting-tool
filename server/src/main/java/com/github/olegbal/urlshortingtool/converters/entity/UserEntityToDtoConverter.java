package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.domain.dto.RoleDto;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserEntityToDtoConverter implements Converter<User, UserDto> {

    private final LinkEntityToDtoConverter linkEntityToDtoConverter;
    private final RoleEntityToDtoConverter roleEntityToDtoConverter;

    @Autowired
    public UserEntityToDtoConverter(LinkEntityToDtoConverter linkEntityToDtoConverter, RoleEntityToDtoConverter roleEntityToDtoConverter) {
        this.linkEntityToDtoConverter = linkEntityToDtoConverter;
        this.roleEntityToDtoConverter = roleEntityToDtoConverter;
    }

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());

        for (Link link : user.getLinkSet()) {
            userDto.getLinks().add(linkEntityToDtoConverter.convert(link));
        }
        for (Role role : user.getRoles()) {
            userDto.getRoles().add(roleEntityToDtoConverter.convert(role));
        }
        return userDto;
    }
}
