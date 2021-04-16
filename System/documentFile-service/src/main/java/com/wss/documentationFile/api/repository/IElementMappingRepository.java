package com.wss.documentationFile.api.repository;

import com.wss.documentationCommon.entity.ElementMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementMappingRepository extends JpaRepository<ElementMappingEntity, String> {
}
