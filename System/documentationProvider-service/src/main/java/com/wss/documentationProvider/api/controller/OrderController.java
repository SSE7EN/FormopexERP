package com.wss.documentationProvider.api.controller;

import com.wss.common.controller.IEntityController;
import com.wss.common.exception.ResourceNotFoundException;
import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.DocumentDTO;
import com.wss.documentationCommon.dto.DocumentationProviderDTO;
import com.wss.documentationCommon.dto.OrderDTO;
import com.wss.documentationProvider.api.request.OrderCreateRequestModel;
import com.wss.documentationProvider.api.request.OrderUpdateRequestModel;
import com.wss.documentationProvider.api.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController implements IEntityController<OrderDTO, OrderCreateRequestModel, OrderUpdateRequestModel> {

    private final IOrderService service;

    public OrderController(@Qualifier("orderServiceImpl") IOrderService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<OrderDTO> insert(@Valid OrderCreateRequestModel request) {
        ModelMapper modelMapper = Util.modelMapperStrict();
        OrderDTO dto = modelMapper.map(request, OrderDTO.class);

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(request.getDocumentId());

        DocumentationProviderDTO documentationProviderDTO = new DocumentationProviderDTO();
        documentationProviderDTO.setId(request.getProviderId());

        dto.setDocument(documentDTO);
        dto.setProvider(documentationProviderDTO);
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<OrderDTO> update(@Valid OrderUpdateRequestModel request) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<OrderDTO> getById(String id) throws ResourceNotFoundException {
        OrderDTO dto = service.findById(id);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        else throw new ResourceNotFoundException("order not found");
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getAll() {
        return null;
    }



    @Override
    public ResponseEntity<Void> delete(String id) throws ResourceNotFoundException {
        return null;
    }
}
