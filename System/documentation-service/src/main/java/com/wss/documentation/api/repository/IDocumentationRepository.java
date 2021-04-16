package com.wss.documentation.api.repository;


import com.wss.documentationCommon.entity.DocumentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentationRepository extends JpaRepository<DocumentationEntity, String> {
}
