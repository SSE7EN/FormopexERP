package com.wss.authservice;

import com.wss.authservice.dto.AppUserDTO;
import com.wss.authservice.entity.AppUserEntity;
import com.wss.authservice.repositories.IAppUserRepository;
import com.wss.authservice.services.IAppUserService;
import com.wss.common.model.EUserPermission;
import com.wss.common.model.EUserRole;
import com.wss.common.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    IAppUserRepository IAppUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Qualifier("appUserServiceImpl")
    @Autowired
    IAppUserService appUserService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test");
        createUsers();
    }


    private void createUsers(){
        AppUserEntity appUserEntity1 = new AppUserEntity("Admin",passwordEncoder.encode("Admin"), "admin@admin", true, EUserRole.ADMIN,EUserPermission.TEST.getCode());
        appUserEntity1.setId(UUID.randomUUID().toString());
        IAppUserRepository.save(appUserEntity1);

        appUserEntity1 = new AppUserEntity("User",passwordEncoder.encode("User"), "user@user", true, EUserRole.USER,EUserPermission.TEST.getCode());
        appUserEntity1.setId(UUID.randomUUID().toString());
        IAppUserRepository.save(appUserEntity1);





    }
}
