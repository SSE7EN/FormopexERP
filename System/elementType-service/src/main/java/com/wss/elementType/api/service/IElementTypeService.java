package com.wss.elementType.api.service;

import com.wss.elementType.api.request.ElementTypeCreateRequestModel;
import com.wss.elementType.api.request.ElementTypeUpdateRequestModel;
import com.wss.projectElementCommon.dto.ElementTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 /**
 * ElementType service interface
 *
 * @author se7en
 */
@Service
public interface IElementTypeService {


    /**
     * Save elementType
     * @param elementDTO
     * * @return
     */
    public ElementTypeDTO insert(ElementTypeDTO elementDTO);

    /**
     * Update elementType
     * @param elementDTO
     * @return
     */
    public ElementTypeDTO update(ElementTypeDTO elementDTO);

    /**
     * Get all element types
     * @return
     */
    public List<ElementTypeDTO> getAll();

    /**
     * find element type by id
     * @param id
     * @return
     */
    public ElementTypeDTO findById(String id);

    /**
     * delete element type by id
     * @param id
     */
    public void delete(String id);

}

