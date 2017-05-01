package com.github.olegbal.urlshortingtool.services.impl;

import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import com.github.olegbal.urlshortingtool.services.ExpiredLinksService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ExpiredLinksServiceImpl implements ExpiredLinksService {

    private static final long repeatInMills = 60 * 1000;

    @Autowired
    private LinkRepository linkRepository;

    //    @Transactional
//    @Scheduled(fixedRate = repeatInMills, initialDelay = repeatInMills)
    public void removeExpiredLinks() {


        Date expirationDate = new Date(System.currentTimeMillis() - repeatInMills);

        try {
            linkRepository.removeLinksByCreationDateBefore(expirationDate);

        } catch (TransactionException ex) {

        }
    }

}
