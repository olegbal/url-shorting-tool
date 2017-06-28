package com.github.olegbal.urlshortingtool.converters.dto;

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

import java.util.HashSet;
import java.util.Set;

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
