package com.wss.itemList.api.service;

import com.wss.projectElementCommon.dto.ElementDTO;
import com.wss.projectElementCommon.dto.ElementWithProjectDTO;
import com.wss.projectElementCommon.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Item service interface
 *
 * @author se7en
 */
@Service
public interface IItemService {

    /**
     * Save item
     * @param itemDTO
     * @return
     */
    public ItemDTO insert(ItemDTO itemDTO);

    /**
     * Update item
     * @param itemDTO
     * @return
     */
    public ItemDTO update(ItemDTO itemDTO);

    /**
     * Get all items
     * @return
     */
    public List<ItemDTO> getAll();

    /**
     * Get item by id
     * @param id
     * @return
     */
    public ItemDTO findById(String id);

    /**
     * Delete item by id;
     * @param id
     */
    public void delete(String id);
}
