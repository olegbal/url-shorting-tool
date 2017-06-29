package com.github.olegbal.urlshortingtool.controllers;

import com.github.olegbal.urlshortingtool.dto.CreatedLinkResponseDto;
import com.github.olegbal.urlshortingtool.dto.LinkDto;
import com.github.olegbal.urlshortingtool.services.LinkService;
import com.github.olegbal.urlshortingtool.services.CustomLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/links")
public class UserLinksController {

    private final LinkService linkService;

    @Autowired
    public UserLinksController(CustomLinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(path = "", params = "userId", method = RequestMethod.GET)
    public ResponseEntity getUsersLinks(Pageable pageable, @RequestParam("userId") long id) {

        return new ResponseEntity<>(linkService.findAllUsersLinks(pageable, id), HttpStatus.OK);
    }

    @PreAuthorize("@userPreAuthorizeService.checkRightsToUrlById(#request,#id)")
    @RequestMapping(path = "", params = "userId", method = RequestMethod.POST)
    public ResponseEntity createUserLink(HttpServletRequest request,
                                         @RequestBody LinkDto linkDto, @RequestParam("userId") long id) {

        CreatedLinkResponseDto responseDto = linkService.createLink(id, linkDto);
        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);

    }

    @PreAuthorize("@userPreAuthorizeService.checkRightsToUrlById(#request,#id)")
    @RequestMapping(path = "", params = "userId", method = RequestMethod.PUT)
    public ResponseEntity updateUserLink(HttpServletRequest request,
                                         @RequestBody LinkDto linkDto, @RequestParam("userId") long id) {

        if (linkService.updateLink(id, linkDto)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
}