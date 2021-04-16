package com.wss.documentation.api.repository;


import com.wss.documentationCommon.entity.ItemMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemMappingRepository extends JpaRepository<ItemMappingEntity,String> {
}
