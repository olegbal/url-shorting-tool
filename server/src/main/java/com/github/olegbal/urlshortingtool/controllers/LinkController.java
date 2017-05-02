package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.domain.dto.CreatedLinkResponseDto;
import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.services.impl.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/links")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity getAllLinks(Pageable pageable) {
        return new ResponseEntity(linkService.findAllLinks(pageable), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getLinkById(@PathVariable long id) {
        return new ResponseEntity(linkService.getLinkById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "", params = "tag", method = RequestMethod.GET)
    public ResponseEntity getLinksByTag(Pageable pageable, @RequestParam("tag") String tag) {

        return new ResponseEntity(linkService.getLinksByTag(pageable, tag), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("@userPreAuthorizeService.checkRightsToLinkUrlByLinkId(#request,#id)")
    public ResponseEntity deleteLinks(HttpServletRequest request, @PathVariable long id) {

        if (linkService.removeLink(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(path = "/check", params = "url", method = RequestMethod.GET)
    public ResponseEntity checkLink(@RequestParam("url") String url) {

        LinkDto link = linkService.getByOriginalLink(url);

        if (link != null) {
            return new ResponseEntity(link, HttpStatus.CONFLICT);
        }
        return new ResponseEntity(link.shortLink,HttpStatus.OK);
    }

}
