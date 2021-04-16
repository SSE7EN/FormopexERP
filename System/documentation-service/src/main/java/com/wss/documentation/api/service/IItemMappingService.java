package com.wss.documentation.api.service;

import com.wss.documentationCommon.dto.ItemMappingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemMappingService {

    public ItemMappingDTO insert(ItemMappingDTO dto);

    public ItemMappingDTO update(ItemMappingDTO dto);

    public List<ItemMappingDTO> getAll();

    public ItemMappingDTO findById(String id);

    public void delete(String id);
}
