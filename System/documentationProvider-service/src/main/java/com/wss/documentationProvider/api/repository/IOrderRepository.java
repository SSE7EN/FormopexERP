package com.wss.documentationProvider.api.repository;

import com.wss.documentationCommon.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, String> {

    public List<OrderEntity> findAllByProviderId(String providerId);
}
