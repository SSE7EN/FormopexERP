package com.wss.itemList.api.repository;

import com.wss.projectElementCommon.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<ItemEntity, String> {
}
