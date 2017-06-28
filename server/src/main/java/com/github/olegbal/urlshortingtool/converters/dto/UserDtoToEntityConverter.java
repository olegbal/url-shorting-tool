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

import java.util.Set;

@Service
public class UserDtoToEntityConverter implements Converter<UserDto, User> {

    private final ConversionService conversionService;

    @Autowired
    public UserDtoToEntityConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public User convert(UserDto userDto) {

        User user = new User();

        user.setUserId(userDto.getUserId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setLinkSet((Set<Link>) conversionService.convert(userDto.getLinks(),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(LinkDto.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Link.class))));
        user.setRoles((Set<Role>) conversionService.convert(userDto.getRoles(),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(RoleDto.class)),
                TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Role.class))));


        return user;

    }
}
