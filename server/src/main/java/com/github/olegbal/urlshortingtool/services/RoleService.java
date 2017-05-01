package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.domain.entity.Role;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface RoleService {

    Role getByRoleName(String roleName);
}
