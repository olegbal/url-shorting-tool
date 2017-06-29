package com.github.olegbal.urlshortingtool.converters.dto;

import com.github.olegbal.urlshortingtool.dto.LinkDto;
import com.github.olegbal.urlshortingtool.dto.RoleDto;
import com.github.olegbal.urlshortingtool.dto.UserDto;
import com.github.olegbal.urlshortingtool.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserDtoToEntityConverter implements Converter<UserDto, User> {

    private final LinkDtoToEntityConverter linkDtoToEntityConverter;
    private final RoleDtoToEntityConverter roleDtoToEntityConverter;

    @Autowired
    public UserDtoToEntityConverter(LinkDtoToEntityConverter linkDtoToEntityConverter, RoleDtoToEntityConverter roleDtoToEntityConverter) {
        this.linkDtoToEntityConverter = linkDtoToEntityConverter;
        this.roleDtoToEntityConverter = roleDtoToEntityConverter;
    }

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        for (LinkDto link : userDto.getLinks()) {
            user.getLinkSet().add(linkDtoToEntityConverter.convert(link));
        }
        for (RoleDto role : userDto.getRoles()) {
            user.getRoles().add(roleDtoToEntityConverter.convert(role));
        }

        return user;

    }
}
