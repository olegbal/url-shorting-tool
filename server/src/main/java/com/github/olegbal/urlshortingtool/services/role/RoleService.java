package com.github.olegbal.urlshortingtool.services.role;

import com.github.olegbal.urlshortingtool.domain.Role;

public interface RoleService {

    //FIXME use Optional here
    Role getByRoleName(String roleName);
}
