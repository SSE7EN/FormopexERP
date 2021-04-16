package com.wss.documentationFile.api.repository;

import com.wss.documentationCommon.entity.DocumentFileEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentationFileRepository extends JpaRepository<DocumentFileEntity, String> {
}
