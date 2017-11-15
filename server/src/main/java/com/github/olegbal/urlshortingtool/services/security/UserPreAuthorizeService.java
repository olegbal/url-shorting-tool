package com.github.olegbal.urlshortingtool.services.security;

import com.github.olegbal.urlshortingtool.domain.Link;
import com.github.olegbal.urlshortingtool.domain.User;
import com.github.olegbal.urlshortingtool.enums.RolesEnum;
import com.github.olegbal.urlshortingtool.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("userPreAuthorizeService")
public class UserPreAuthorizeService {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final LinkRepository linkService;

    @Autowired
    public UserPreAuthorizeService(TokenAuthenticationService tokenAuthenticationService, LinkRepository linkService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.linkService = linkService;
    }


    public boolean checkRightsToUrlById(HttpServletRequest request, long id) {

        User requestingUser = tokenAuthenticationService.getTokenHandler().parseUserFromToken(request.getHeader("Auth"));


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

    public boolean hasAdminRole(HttpServletRequest request) {
        User requestingUser = tokenAuthenticationService.tokenHandler.parseUserFromToken(request.getHeader("Auth"));

        if (requestingUser != null && requestingUser.getRoles().stream().anyMatch(x -> x.getRoleName().equals(RolesEnum.ADMIN.role_name))) {
            return true;
        }
        return false;
    }
}
