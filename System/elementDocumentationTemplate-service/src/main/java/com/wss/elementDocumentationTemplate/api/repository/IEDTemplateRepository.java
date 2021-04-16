package com.wss.elementDocumentationTemplate.api.repository;

import com.wss.documentationTemplate.entity.ElementDocumentationTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEDTemplateRepository extends JpaRepository<ElementDocumentationTemplateEntity, String> {
}
