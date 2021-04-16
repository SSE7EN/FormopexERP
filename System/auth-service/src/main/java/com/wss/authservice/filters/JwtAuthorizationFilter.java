package com.wss.authservice.filters;


import com.wss.authservice.JwtProperties;
import com.wss.authservice.entity.AppUserEntity;
import com.wss.authservice.repositories.IAppUserRepository;
import com.wss.authservice.services.UserPrincipal;
import com.wss.authservice.util.JwtUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE + 98)
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private IAppUserRepository IAppUserRepository;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, IAppUserRepository IAppUserRepository) {
        super(authenticationManager);
        this.IAppUserRepository = IAppUserRepository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Read the Authorization header, where the JWT token should be

        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {

        final String authorizationHeader = request.getHeader(JwtProperties.HEADER_STRING);

        String email = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            jwt = authorizationHeader.substring(7);
            email = JwtUtil.extractEmail(jwt);
        }


        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            AppUserEntity appUserEntity = this.IAppUserRepository.findByEmail(email);
            if(appUserEntity == null) return null;

            if(JwtUtil.validateToken(jwt, appUserEntity)) {
                UserPrincipal userPrincipal = new UserPrincipal(appUserEntity);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userPrincipal, null,
                        userPrincipal.getAuthorities());

                return auth;

            }
        }
        return null;
    }
}
