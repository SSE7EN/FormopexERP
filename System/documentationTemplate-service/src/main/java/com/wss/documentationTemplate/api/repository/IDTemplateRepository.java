package com.wss.documentationTemplate.api.repository;

import com.wss.documentationTemplate.entity.DocumentationTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDTemplateRepository extends JpaRepository<DocumentationTemplateEntity, String> {
}
