package com.wss.documentationProvider.api.repository;

import com.wss.documentationCommon.dto.UserMappingDTO;
import com.wss.documentationCommon.entity.UserMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserMappingRepository extends JpaRepository<UserMappingEntity,String> {

    public Optional<UserMappingEntity> findByUserId(String userId);
}
