package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.services.impl.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/v1/links")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity getAllLinks() {
        return new ResponseEntity(linkService.findAllLinks(), HttpStatus.OK);
    }


    @RequestMapping(path = "", params = "userId", method = RequestMethod.GET)
    public ResponseEntity getUsersLinks(@RequestParam("userId") long id) {

        return new ResponseEntity(linkService.findAllUsersLinks(id), HttpStatus.OK);
    }

    @RequestMapping(path = "", params = "tag", method = RequestMethod.GET)
    public ResponseEntity getLinksByTag(@RequestParam("tag") String tag) {

        return new ResponseEntity(linkService.getLinksByTag(tag), HttpStatus.OK);
    }

    @PostAuthorize("@userPreAuthorizeService.checkRightsToUrlById(#request,#id)")
    @RequestMapping(path = "", params = "userId", method = RequestMethod.POST)
    public ResponseEntity createUserLink(HttpServletRequest request, @RequestBody LinkDto linkDto, @RequestParam("userId") long id) {

        long resultId = 0;
        resultId = linkService.createLink(id, linkDto);
        if (resultId != 0) {
            return new ResponseEntity(resultId, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);

    }

    @PostAuthorize("@userPreAuthorizeService.checkRightsToUrlById(#request,#id)")
    @RequestMapping(path = "", params = "userId", method = RequestMethod.PUT)
    public ResponseEntity updateUserLink(HttpServletRequest request, @RequestBody LinkDto linkDto, @RequestParam("userId") long id) {

        if (linkService.updateLink(id, linkDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
    @PostAuthorize("@userPreAuthorizeService.checkRightsToLinkUrlByLinkId(#request,#id)")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLinks(HttpServletRequest request, @PathVariable long id) {

        if (linkService.removeLink(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getLinkById(@PathVariable long id) {
        return new ResponseEntity(linkService.getLinkById(id), HttpStatus.OK);
    }

}
