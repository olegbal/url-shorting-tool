package com.github.olegbal.urlshortingtool.repositories;

import com.github.olegbal.urlshortingtool.domain.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface LinkRepository extends JpaRepository<Link, Long> {

    //FIXME investigate, where to store dat queries
    @Query(value = "SELECT * from user_links WHERE BINARY short_link = ?1", nativeQuery = true)
    Link findByShortLink(String shortLink);

    Page<Link> findByUserUserId(Pageable pageable, long userId);

    void removeLinksByCreationDateBefore(Date date);

    @Query(value = "SELECT * from user_links WHERE BINARY original_link = ?1", nativeQuery = true)
    Link findByOriginalLink(String originalLink);
}
