package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.converters.SetConverter;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserEntityToDtoConverter extends SetConverter<User, UserDto> implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {

        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(new RoleEntityToDtoConverter().convertSet(user.getRoles()));
        userDto.setLinks(new LinkEntityToDtoConverter().convertSet(user.getLinkSet()));
        return userDto;
    }
}
