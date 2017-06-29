package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.dto.RoleDto;
import com.github.olegbal.urlshortingtool.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class RoleEntityToDtoConverter implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
