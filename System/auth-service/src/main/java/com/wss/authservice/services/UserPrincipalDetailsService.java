package com.wss.authservice.services;


import com.wss.authservice.entity.AppUserEntity;
import com.wss.authservice.repositories.IAppUserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories(basePackages = "meetup.security.repositories")
@EntityScan(basePackages = "meetup.security.models")
public class UserPrincipalDetailsService implements UserDetailsService {



    private IAppUserRepository IAppUserRepository;

    public UserPrincipalDetailsService(IAppUserRepository IAppUserRepository){
        this.IAppUserRepository = IAppUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUserEntity appUserEntity = this.IAppUserRepository.findByEmail(s);
        if(appUserEntity == null) throw new UsernameNotFoundException("Email does not exist");
        UserPrincipal userPrincipal = new UserPrincipal(appUserEntity);
        return userPrincipal;
    }
}


