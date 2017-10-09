package com.github.olegbal.urlshortingtool.repositories;

import com.github.olegbal.urlshortingtool.domain.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface LinkRepository extends JpaRepository<Link, Long> {


    Link findByShortLink(String shortLink);

    Page<Link> findByUserUserId(Pageable pageable, long userId);

    void removeLinksByCreationDateBefore(Date date);

    Link findByOriginalLink(String originalLink);
}
