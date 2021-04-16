package com.wss.documentationProvider.api.repository;

import com.wss.documentationCommon.entity.DocumentationProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentationProviderRepository extends JpaRepository<DocumentationProviderEntity, String> {


}
