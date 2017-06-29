package com.github.olegbal.urlshortingtool.repositories;

import com.github.olegbal.urlshortingtool.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * from users WHERE BINARY login = ?1", nativeQuery = true)
    User findByLogin(String login);

}
