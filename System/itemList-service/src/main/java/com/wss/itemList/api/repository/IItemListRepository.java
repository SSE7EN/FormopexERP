package com.wss.itemList.api.repository;

import com.wss.projectElementCommon.entity.ItemListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemListRepository extends JpaRepository<ItemListEntity, String> {

}
