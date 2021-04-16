package com.wss.elementType.api.repository;

import com.wss.projectElementCommon.entity.ElementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementTypeRepository extends JpaRepository<ElementTypeEntity, String> {
}
