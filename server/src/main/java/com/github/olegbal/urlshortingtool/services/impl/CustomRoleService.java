package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.respositories.RoleRepository;
import com.github.olegbal.urlshortingtool.services.RoleService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomRoleService implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public CustomRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByRoleName(String roleName) {

        try {
            Role roles = roleRepository.findByRoleName(roleName);

            return roles;
        } catch (Exception ex) {
            return null;
        }

    }
}
