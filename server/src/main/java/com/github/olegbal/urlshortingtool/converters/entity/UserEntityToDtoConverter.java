package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.Link;
import com.github.olegbal.urlshortingtool.domain.Role;
import com.github.olegbal.urlshortingtool.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

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
