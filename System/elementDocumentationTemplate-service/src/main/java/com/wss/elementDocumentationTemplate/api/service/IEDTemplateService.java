package com.wss.elementDocumentationTemplate.api.service;

import com.wss.documentationTemplate.dto.ElementDocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEDTemplateService {

    /**
     * Save eDTemplateDTO
     * @param eDTemplateDTO
     * @return
     */
    public ElementDocumentationTemplateDTO insert (ElementDocumentationTemplateDTO eDTemplateDTO);

    /**
     * Update eDTemplateDTO
     * @param eDTemplateDTO
     * @return
     */
    public ElementDocumentationTemplateDTO update (ElementDocumentationTemplateDTO eDTemplateDTO);

    /**
     * Get all eDTemplateDTO
     * @return
     */
    public List<ElementDocumentationTemplateDTO> getAll();

    /**
     * Get an eDTemplateDTO by id
     * @param id
     * @return
     */
    public ElementDocumentationTemplateDTO  findById(String id);



    /**
     * Delete a eDTemplateDTO by id
     * @param id
     */
    public void delete(String id);
}
