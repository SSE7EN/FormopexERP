package com.wss.documentationProvider.api.service;

import com.wss.common.service.IEntityService;
import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.OrderDTO;
import com.wss.documentationCommon.entity.OrderEntity;
import com.wss.documentationProvider.api.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository repository;

    public OrderServiceImpl(IOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderDTO insert(OrderDTO dto) {
        ModelMapper modelMapper = Util.modelMapperStrict();
        OrderEntity orderEntity = modelMapper.map(dto, OrderEntity.class);

        orderEntity.setId(UUID.randomUUID().toString());

        orderEntity = repository.save(orderEntity);
        dto = modelMapper.map(orderEntity, OrderDTO.class);

        return dto;
    }

    @Override
    public OrderDTO update(OrderDTO dto) {
        return null;
    }

    @Override
    public List<OrderDTO> getAll() {
        return null;
    }

    @Override
    public OrderDTO findById(String id) {
        OrderEntity orderEntity = repository.findById(id).orElse(null);

        if(orderEntity == null) return null;

        ModelMapper modelMapper = Util.modelMapperStrict();

        OrderDTO orderDTO = modelMapper.map(orderEntity, OrderDTO.class);
        return orderDTO;



    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<OrderDTO> findAllByProviderId(String id) {
        List<OrderEntity> orderEntities = repository.findAllByProviderId(id);
        ModelMapper modelMapper = Util.modelMapperStrict();

        List<OrderDTO> orderDTOS = orderEntities
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDTO.class))
                .collect(Collectors.toList());

        return orderDTOS;
    }
}
