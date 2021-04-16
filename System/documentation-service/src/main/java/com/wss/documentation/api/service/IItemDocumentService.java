package com.wss.documentation.api.service;

import com.wss.documentationCommon.dto.ElementDocumentDTO;
import com.wss.documentationCommon.dto.ItemDocumentDTO;
import com.wss.documentationTemplate.entity.EItemDocumentationType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IItemDocumentService{



    public ItemDocumentDTO insert(ItemDocumentDTO dto, EItemDocumentationType type);

    public ItemDocumentDTO update(ItemDocumentDTO dto);

    public List<ItemDocumentDTO> getAll();

    public ItemDocumentDTO findById(String id);

    public void delete(String id);

}
