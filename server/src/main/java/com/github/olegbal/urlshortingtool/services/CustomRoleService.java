package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.Role;
import com.github.olegbal.urlshortingtool.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
