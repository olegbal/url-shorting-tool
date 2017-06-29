package com.github.olegbal.urlshortingtool.converters.dto;

import com.github.olegbal.urlshortingtool.dto.RoleDto;
import com.github.olegbal.urlshortingtool.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class RoleDtoToEntityConverter implements Converter<RoleDto, Role> {

    @Override
    public Role convert(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }
}
