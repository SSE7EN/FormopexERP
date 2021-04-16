package com.wss.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthUser implements UserDetails {

    private static final long serialVersionUID = 2061709915172252885L;
    private String userId;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;

    public AuthUser(String userId, String username, String email, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "AuthUser [userId=" + userId + ", username=" + username +", email="+email+", authorities=" + authorities + ", enabled="
                + enabled + "]";
    }

    public String getEmail() {
        return email;
    }

}
