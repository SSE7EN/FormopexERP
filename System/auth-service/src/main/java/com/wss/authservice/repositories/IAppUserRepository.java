package com.wss.authservice.repositories;


import com.wss.authservice.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUserEntity, String> {

    AppUserEntity findByUsername(String username);
    AppUserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
