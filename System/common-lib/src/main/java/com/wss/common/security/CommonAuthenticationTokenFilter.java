package com.wss.common.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommonAuthenticationTokenFilter extends OncePerRequestFilter {

    private String tokenHeader;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authToken = request.getHeader(JwtProperties.HEADER_STRING);

        if (!StringUtils.isEmpty(authToken) && SecurityContextHolder.getContext().getAuthentication() == null) {

            try{

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", authToken);
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

                HttpEntity<String> entity = new HttpEntity<String>("", headers);

                ResponseEntity<String> responseEntity =
                        restTemplate.exchange(
                                "http://AUTH-SERVICE/auth/current"
                                , HttpMethod.POST
                                , entity
                                , String.class);

                System.out.println(entity);

                String jsonUserDetails = responseEntity.getBody();
                UserDetails userDetails = prepareUserDetails(jsonUserDetails);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }catch(Exception e){
                logger.error(e.getMessage());
            }

        }

        chain.doFilter(request, response);
    }

    private UserDetails prepareUserDetails(String jsonUserDetails) throws JsonProcessingException, IOException{



        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonUserDetails);

        String userId = root.get("appUser").get("id").asText();
        String username = root.get("username").asText();
        String email = root.get("email").asText();
        boolean isEnabled =  root.get("enabled").asBoolean();


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Iterator<JsonNode> authoritiesIterator = root.get("authorities").elements();
        while(authoritiesIterator.hasNext()){
            JsonNode authorityNode = authoritiesIterator.next();
            authorities.add(new SimpleGrantedAuthority(authorityNode.get("authority").asText()));
        }

        return new AuthUser(userId, username, email,authorities, isEnabled);
    }

}
