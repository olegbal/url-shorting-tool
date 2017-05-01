package com.github.olegbal.urlshortingtool.respositories;

import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
