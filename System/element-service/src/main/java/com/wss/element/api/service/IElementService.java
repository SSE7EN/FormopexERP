package com.wss.element.api.service;

import com.wss.projectElementCommon.dto.ElementDTO;
import com.wss.projectElementCommon.dto.ElementWithProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Element service interface
 *
 * @author se7en
 */
@Service
public interface IElementService {

    /**
     * Save element
     * @param elementDTO
     * @return
     */
    public List<ElementDTO> insert(ElementWithProjectDTO elementDTO, int count );


    /**
     * Update element
     * @param elementDTO
     * @return
     */
    public ElementWithProjectDTO update(ElementWithProjectDTO elementDTO);

    /**
     * Get all elements
     * @return
     */
    public List<ElementDTO> getAll();


    /**
     * Get element by id
     * @param id
     * @return
     */
    public ElementDTO findById(String id);

    /**
     * Delete element by id;
     * @param id
     */
    public void delete(String id);
}
