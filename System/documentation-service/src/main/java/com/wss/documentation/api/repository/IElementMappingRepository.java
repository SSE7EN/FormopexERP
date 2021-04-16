package com.wss.documentation.api.repository;

import com.wss.documentationCommon.entity.ElementMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IElementMappingRepository extends JpaRepository<ElementMappingEntity,String> {

    public List<ElementMappingEntity> findAllByProjectId(String projectId);

}
