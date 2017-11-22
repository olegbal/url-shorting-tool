package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.dto.LinkDto;
import com.github.olegbal.urlshortingtool.services.link.LinkService;
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

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity getAllLinks(Pageable pageable) {
        return new ResponseEntity<>(linkService.findAllLinks(pageable), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getLinkById(@PathVariable long id) {
        return new ResponseEntity<>(linkService.getLinkById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "", params = "tag", method = RequestMethod.GET)
    public ResponseEntity getLinksByTag(Pageable pageable, @RequestParam("tag") String tag) {

        return new ResponseEntity<>(linkService.getLinksByTag(pageable, tag), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("@userPreAuthorizeService.checkRightsToLinkUrlByLinkId(#request,#id)")
    public ResponseEntity deleteLinks(HttpServletRequest request, @PathVariable long id) {

        if (linkService.removeLink(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public ResponseEntity checkLink(@RequestBody String url) {

        LinkDto link = linkService.getByOriginalLink(url.replace("\"", ""));

        if (link != null) {
            return new ResponseEntity<>(link, HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
