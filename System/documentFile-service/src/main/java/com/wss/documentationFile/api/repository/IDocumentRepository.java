package com.wss.documentationFile.api.repository;

import com.wss.documentationCommon.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentRepository extends JpaRepository<DocumentEntity, String> {

}
