package com.wss.itemDocumentationTemplate.api.repository;

import com.wss.documentationTemplate.entity.ItemDocumentationTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIDTemplateRepository extends JpaRepository<ItemDocumentationTemplateEntity, String> {
}
