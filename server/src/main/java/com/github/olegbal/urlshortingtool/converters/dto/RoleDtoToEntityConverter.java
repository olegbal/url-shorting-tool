package com.github.olegbal.urlshortingtool.converters.dto;

import com.github.olegbal.urlshortingtool.converters.SetConverter;
import com.github.olegbal.urlshortingtool.domain.dto.RoleDto;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleDtoToEntityConverter extends SetConverter<RoleDto, Role> implements Converter<RoleDto, Role> {

    @Override
    public Role convert(RoleDto roleDto) {

        Role role = new Role();

        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());

        return role;
    }
}
