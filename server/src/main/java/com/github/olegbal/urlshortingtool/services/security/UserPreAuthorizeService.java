package com.github.olegbal.urlshortingtool.services.security;

import com.github.olegbal.urlshortingtool.domain.entity.Link;
import com.github.olegbal.urlshortingtool.domain.entity.User;
import com.github.olegbal.urlshortingtool.enums.RolesEnum;
import com.github.olegbal.urlshortingtool.respositories.LinkRepository;
import com.github.olegbal.urlshortingtool.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("userPreAuthorizeService")
public class UserPreAuthorizeService {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;


    @Autowired
    private LinkRepository linkService;


    public boolean checkRightsToUrlById(HttpServletRequest request, long id) {

        User requestingUser = tokenAuthenticationService.tokenHandler.parseUserFromToken(request.getHeader("Auth"));


        if (requestingUser != null) {
            if (requestingUser.getUserId() == id
                    || requestingUser.getRoles().stream().anyMatch(x -> x.getRoleName().equals(RolesEnum.ADMIN.role_name))) {
                return true;
            }
        }

        return false;
    }

    public boolean checkRightsToUrlByUsername(HttpServletRequest request, String userName) {

        User requestingUser = tokenAuthenticationService.tokenHandler.parseUserFromToken(request.getHeader("Auth"));


        if (requestingUser != null) {
            if (requestingUser.getUsername().equals(userName)
                    || requestingUser.getRoles().stream().anyMatch(x -> x.getRoleName().equals(RolesEnum.ADMIN.role_name))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRightsToLinkUrlByLinkId(HttpServletRequest request, long id) {

        User requestingUser = tokenAuthenticationService.tokenHandler.parseUserFromToken(request.getHeader("Auth"));

        Link link = linkService.findOne(id);

        //Any operation with requestingUser.getLinkSet()
        // dont allow to execute repository.delete(id) method.

        if (requestingUser != null && link != null) {
            if (link.getUser().getUserId() == requestingUser.getUserId()
                    || requestingUser.getRoles().stream().anyMatch(x -> x.getRoleName().equals("ROLE_ADMIN"))) {
                return true;
            }
        }
        return false;
    }
}
