package com.github.olegbal.urlshortingtool.respositories;

import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.Role;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.Set;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(value = "SELECT * from user_links WHERE BINARY short_link = ?1", nativeQuery = true)
    Link findByShortLink(String shortLink);

    Page<Link> findByUserUserId(Pageable pageable, long userId);

    void removeLinksByCreationDateBefore(Date date);

    @Query(value = "SELECT * from user_links WHERE BINARY original_link = ?1", nativeQuery = true)
    Link findByOriginalLink(String originalLink);
}
