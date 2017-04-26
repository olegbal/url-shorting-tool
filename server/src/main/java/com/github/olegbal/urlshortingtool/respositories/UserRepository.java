package com.github.olegbal.urlshortingtool.respositories;

import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
