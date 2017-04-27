package com.github.olegbal.urlshortingtool.respositories;

import com.github.olegbal.urlshortingtool.domain.entity.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LinkRepository extends CrudRepository<Link, Long> {

    Link findByShortLink(String shortLink);

    Set<Link> findByUserUserId(long userId);
}
