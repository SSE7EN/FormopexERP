package com.wss.documentationTemplate.api.service;

import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ElementDocumentationTemplateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDTemplateService {

    /**
     * Save dTemplateDTO
     * @param dTemplateDTO
     * @return
     */
    public DocumentationTemplateDTO insert (DocumentationTemplateDTO dTemplateDTO);

    /**
     * Update dTemplateDTO
     * @param dTemplateDTO
     * @return
     */
    public DocumentationTemplateDTO update (DocumentationTemplateDTO dTemplateDTO);

    /**
     * Get all dTemplateDTO
     * @return
     */
    public List<DocumentationTemplateDTO> getAll();

    /**
     * Get an dTemplateDTO by id
     * @param id
     * @return
     */
    public DocumentationTemplateDTO  findById(String id);



    /**
     * Delete a dTemplateDTO by id
     * @param id
     */
    public void delete(String id);


}
