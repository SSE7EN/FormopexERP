package com.wss.itemDocumentationTemplate.api.service;

import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import com.wss.documentationTemplate.entity.EItemDocumentationType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IIDTemplateService {

    /**
     * Save IIDTemplate
     * @param iDTemplateDTO
     * @return
     */
    public ItemDocumentationTemplateDTO insert (ItemDocumentationTemplateDTO iDTemplateDTO);

    /**
     * Update IIDTemplate
     * @param iDTemplateDTO
     * @return
     */
    public ItemDocumentationTemplateDTO update (ItemDocumentationTemplateDTO iDTemplateDTO);

    /**
     * Get all IIDTemplates
     * @return
     */
    public List<ItemDocumentationTemplateDTO> getAll();

    /**
     * Get an IIDTemplate by id
     * @param id
     * @return
     */
    public ItemDocumentationTemplateDTO  findById(String id);


    public List<EItemDocumentationType> getAllItemDocumentationTypes();

    /**
     * Delete a IIDTemplate by id
     * @param id
     */
    public void delete(String id);
}
