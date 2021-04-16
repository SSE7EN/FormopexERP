package com.wss.itemType.api.repository;

import com.wss.projectElementCommon.entity.ItemTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemTypeRepository extends JpaRepository<ItemTypeEntity, String> {
}
