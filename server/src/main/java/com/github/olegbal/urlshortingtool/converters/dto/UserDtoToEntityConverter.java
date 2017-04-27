package com.github.olegbal.urlshortingtool.converters.dto;

import com.github.olegbal.urlshortingtool.converters.SetConverter;
import com.github.olegbal.urlshortingtool.domain.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToEntityConverter extends SetConverter<UserDto, User> implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {

        User user = new User();

        user.setUserId(userDto.getUserId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setLinkSet(new LinkDtoToEntityConverter().convertSet(userDto.getLinks()));
        user.setRoles(new RoleDtoToEntityConverter().convertSet(userDto.getRoles()));


        return user;

    }
}
