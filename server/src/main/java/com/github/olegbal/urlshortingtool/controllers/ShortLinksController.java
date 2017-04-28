package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.services.impl.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ShortLinksController {

    @Autowired
    private LinkServiceImpl linkService;

    @RequestMapping("/api/v1/shortlinks/{linkValue}")
    public RedirectView redirectToOriginalLink(@PathVariable String linkValue) {

        String originalLink = linkService.checkLink(linkValue);

        if (originalLink != null) {
            return new RedirectView(originalLink, false);
        }
        return new RedirectView("/");
    }

}
