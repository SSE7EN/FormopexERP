package com.wss.documentation.api.repository;


import com.wss.documentationCommon.entity.ElementDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementDocumentRepository extends JpaRepository<ElementDocumentEntity,String> {
}
