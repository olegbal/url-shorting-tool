package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.domain.dto.LinkDto;
import com.github.olegbal.urlshortingtool.services.impl.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/")
public class LinkController {

    @Autowired
    private LinkServiceImpl linkService;

    @RequestMapping(path = "links", method = RequestMethod.GET)
    public ResponseEntity getAllLinks() {
        return new ResponseEntity(linkService.findAllLinks(), HttpStatus.OK);
    }


    @RequestMapping(path = "links", params = "userId", method = RequestMethod.GET)
    public ResponseEntity getUsersLinks(@RequestParam("userId") long id) {

        return new ResponseEntity(linkService.findAllUsersLinks(id), HttpStatus.OK);
    }

    @RequestMapping(path = "links", params = "tag", method = RequestMethod.GET)
    public ResponseEntity getLinksByTag(@RequestParam("tag") String tag) {

        return new ResponseEntity(linkService.getLinksByTag(tag), HttpStatus.OK);
    }


    @RequestMapping(path = "links", params = "userId", method = RequestMethod.POST)
    public ResponseEntity createUserLink(@RequestBody LinkDto linkDto, @RequestParam("userId") long id) {

        if (linkService.updateLink(id, linkDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);

    }

    @RequestMapping(path = "links", params = "userId", method = RequestMethod.PUT)
    public ResponseEntity updateUserLink(@RequestBody LinkDto linkDto, @RequestParam("userId") long id) {

        if (linkService.updateLink(id, linkDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(path = "links/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLinks(@PathVariable long id) {

        if (linkService.removeLink(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

}
