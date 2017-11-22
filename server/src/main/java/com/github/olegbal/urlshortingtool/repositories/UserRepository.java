package com.github.olegbal.urlshortingtool.repositories;

import com.github.olegbal.urlshortingtool.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);
}
