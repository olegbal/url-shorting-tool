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
public class ExpiredLinksServiceImpl implements ExpiredLinksService {

    @Autowired
    private LinkRepository linkRepository;

    @Transactional
    @Scheduled(fixedRate = 60 * 1000, initialDelay = 60 * 1000)
    public void removeExpiredLinks() {

        Date expirationDate = new Date(System.currentTimeMillis() - 60 * 1000);

        try {
            linkRepository.removeLinksByCreationDateBefore(expirationDate);

        } catch (TransactionException ex) {

        }
    }

}
