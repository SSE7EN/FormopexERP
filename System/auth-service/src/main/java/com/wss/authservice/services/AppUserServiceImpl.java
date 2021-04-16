package com.wss.authservice.services;



import com.wss.authservice.JwtProperties;
import com.wss.authservice.dto.AppUserDTO;
import com.wss.authservice.entity.AppUserEntity;
import com.wss.authservice.repositories.IAppUserRepository;
import com.wss.authservice.util.JwtUtil;
import com.wss.common.exception.RequestException;
import com.wss.common.model.EUserPermission;
import com.wss.common.model.EUserRole;
import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.UserMappingDTO;
import com.wss.documentationCommon.request.UserMappingCreateRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserServiceImpl implements IAppUserService{


    private final IAppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final WebClient.Builder webClient;


    public AppUserServiceImpl(IAppUserRepository repository, PasswordEncoder passwordEncoder, WebClient.Builder webClient) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.webClient = webClient;
    }




    @Override
    public AppUserDTO insert(AppUserDTO dto) {
        ModelMapper modelMapper = Util.modelMapperStrict();

        AppUserEntity appUserEntity = modelMapper.map(dto, AppUserEntity.class);
        appUserEntity.setId(UUID.randomUUID().toString());
        appUserEntity = repository.save(appUserEntity);

        dto = modelMapper.map(appUserEntity, AppUserDTO.class);

        return dto;

    }

    @Override
    public AppUserDTO update(AppUserDTO dto) {
        ModelMapper modelMapper = Util.modelMapperStrict();
        AppUserEntity appUserEntity = modelMapper.map(dto, AppUserEntity.class);

        appUserEntity = repository.save(appUserEntity);

        dto = modelMapper.map(appUserEntity, AppUserDTO.class);

        return dto;
    }

    @Override
    public List<AppUserDTO> getAll() {
        ModelMapper modelMapper = Util.modelMapperStrict();
        ArrayList<AppUserEntity> users = (ArrayList<AppUserEntity>) repository.findAll();

        List<AppUserDTO> usersDTO = users
                .stream()
                .map(appUserEntity -> modelMapper.map(appUserEntity, AppUserDTO.class))
                .collect(Collectors.toList());


        return usersDTO;
    }

    @Override
    public AppUserDTO findById(String id) {
        AppUserEntity appUserEntity = repository.findById(id).orElse(null);
        if(appUserEntity == null) return null;

        ModelMapper modelMapper = Util.modelMapperStrict();
        AppUserDTO dto = modelMapper.map(appUserEntity, AppUserDTO.class);

        return dto;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public boolean emailExist(String email) {
        return repository.existsByEmail(email);
    }


    @Override
    public AppUserDTO setRolesAndPermissions(AppUserDTO appUserDTO,Set<EUserRole> roles, Set<EUserPermission> permissions) {
//        List<String> tempUserRoles = Arrays.asList(appUserDTO.getRoles().split(","));
//        Set<EUserRole> eUserRoles = tempUserRoles
//                .stream()
//                .map(EUserRole::valueOf)
//                .collect(Collectors.toSet());
//        eUserRoles.addAll(roles);

        String userRoles = String.join(",",roles.stream().map(EUserRole::getCode).collect(Collectors.toList()));
        appUserDTO.setRoles(userRoles);


        List<String> tempUserPermissions = Arrays.asList(appUserDTO.getPermissions().split(","));
        Set<EUserPermission> eUserPermissions = tempUserPermissions
                .stream()
                .map(perm -> EUserPermission.getEnum(perm))
                .collect(Collectors.toSet());

        for(EUserPermission permission : permissions){
            if(eUserPermissions.contains(permission)) continue;
            initializePermission(appUserDTO,permission);
            permissions.add(permission);
        }

        String userPermissions = String.join(",",permissions.stream().map(EUserPermission::getCode).collect(Collectors.toList()));
        appUserDTO.setPermissions(userPermissions);


        return update(appUserDTO);



    }


    private void initializePermission(AppUserDTO appUserDTO,EUserPermission permission){

        switch (permission){
            case PROVIDE_DOCUMENTATION:
                requestUserDocumentationProviderInitialization(appUserDTO);
                break;
        }

    }

    private void requestUserDocumentationProviderInitialization(AppUserDTO appUserDTO){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserMappingCreateRequestModel createRequestModel = new UserMappingCreateRequestModel();
        createRequestModel.setUserId(appUserDTO.getId());

        ResponseEntity<String> response = this.webClient.build()
                .post()
                .uri("http://DOCUMENTATIONPROVIDER-SERVICE/userMapping/initialize")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, JwtProperties.TOKEN_PREFIX + JwtUtil.generateToken(userPrincipal))
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(createRequestModel)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RequestException(error.statusCode(),"API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RequestException(error.statusCode(), "Server is not responding")))
                .toEntity(String.class)
                .block();

        

        System.out.println(response.getStatusCode());
    }


    private void requestDocumentationProviderSetup(String jwt){
        UserMappingCreateRequestModel createRequestModel = new UserMappingCreateRequestModel();

        ResponseEntity<UserMappingDTO> response = this.webClient.build()
                .post()
                .uri("http://DOCUMENTATIONPROVIDER-SERVICE/userMapping/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .bodyValue(createRequestModel)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RequestException(error.statusCode(),"API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RequestException(error.statusCode(), "Server is not responding")))
                .toEntity(UserMappingDTO.class)
                .block();
    }
}
