package com.wss.authservice.controller;

import com.wss.common.exception.UnAuthorizedAccessException;
import com.wss.common.util.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends ControllerBase {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/current")
    public ResponseEntity<UserDetails> getCurrent() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String authenticatedUserName = authentication.getName();
        if(authenticatedUserName.equals("anonymousUser"))
            throw new UnAuthorizedAccessException(authenticatedUserName);
        else
            return makeResponse((UserDetails)authentication.getPrincipal());
    }
}
