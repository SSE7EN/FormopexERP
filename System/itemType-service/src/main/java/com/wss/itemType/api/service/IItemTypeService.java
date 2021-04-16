package com.wss.itemType.api.service;

import com.wss.projectElementCommon.dto.ElementTypeDTO;
import com.wss.projectElementCommon.dto.ItemTypeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemTypeService {

    /**
     * Save elementType
     * @param itemTypeDTO
     * * @return
     */
    public ItemTypeDTO insert(ItemTypeDTO itemTypeDTO);

    /**
     * Update elementType
     * @param itemTypeDTO
     * @return
     */
    public ItemTypeDTO update(ItemTypeDTO itemTypeDTO);

    /**
     * Get all element types
     * @return
     */
    public List<ItemTypeDTO> getAll();

    /**
     * find element type by id
     * @param id
     * @return
     */
    public ItemTypeDTO findById(String id);

    /**
     * delete element type by id
     * @param id
     */
    public void delete(String id);
}
