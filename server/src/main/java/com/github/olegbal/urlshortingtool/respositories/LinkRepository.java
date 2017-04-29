package com.github.olegbal.urlshortingtool.respositories;

import com.github.olegbal.urlshortingtool.domain.entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {

    Link findByShortLink(String shortLink);

    Page<Link> findByUserUserId(Pageable pageable, long userId);

    void removeLinksByCreationDateBefore(Date date);
}
