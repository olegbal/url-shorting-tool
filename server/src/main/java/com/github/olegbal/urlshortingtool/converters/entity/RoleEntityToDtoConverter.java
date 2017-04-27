package com.github.olegbal.urlshortingtool.converters.entity;

import com.github.olegbal.urlshortingtool.converters.SetConverter;
import com.github.olegbal.urlshortingtool.domain.dto.RoleDto;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleEntityToDtoConverter extends SetConverter<Role, RoleDto> implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(Role role) {

        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());

        return roleDto;
    }
}
