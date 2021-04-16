package com.wss.itemList.api.service;

import com.wss.projectElementCommon.dto.ItemDTO;
import com.wss.projectElementCommon.dto.ItemListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Item list service interface
 *
 * @author se7en
 */
@Service
public interface IItemListService {
    /**
     * Save itemList
     * @param itemListDTO
     * @return
     */
    public ItemListDTO insert(ItemListDTO itemListDTO);

    /**
     * Update itemList
     * @param itemListDTO
     * @return
     */
    public ItemListDTO update(ItemListDTO itemListDTO);

    /**
     * Get all itemLists
     * @return
     */
    public List<ItemListDTO> getAll();

    /**
     * Get itemList by id
     * @param id
     * @return
     */
    public ItemListDTO findById(String id);

    /**
     * Delete itemList by id;
     * @param id
     */
    public void delete(String id);

}
