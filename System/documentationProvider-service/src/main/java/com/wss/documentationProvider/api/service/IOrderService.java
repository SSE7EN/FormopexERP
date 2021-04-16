package com.wss.documentationProvider.api.service;

import com.wss.common.service.IEntityService;
import com.wss.documentationCommon.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService extends IEntityService<OrderDTO> {

    /**
     * Find all by providers id
     * @param id
     * @return
     */
    List<OrderDTO> findAllByProviderId(String id);

}
