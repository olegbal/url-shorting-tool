package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import com.github.olegbal.urlshortingtool.services.ExpiredLinksService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;


@Service
public class CustomExpiredLinksService implements ExpiredLinksService {

    private static final long repeatInMills = 24*60*60 * 1000;

    private final LinkRepository linkRepository;

    @Autowired
    public CustomExpiredLinksService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Transactional
    @Scheduled(fixedRate = repeatInMills, initialDelay = repeatInMills)
    public void removeExpiredLinks() {


        Date expirationDate = new Date(System.currentTimeMillis() - repeatInMills);

        try {
            linkRepository.removeLinksByCreationDateBefore(expirationDate);

        } catch (TransactionException ex) {

        }
    }

}
