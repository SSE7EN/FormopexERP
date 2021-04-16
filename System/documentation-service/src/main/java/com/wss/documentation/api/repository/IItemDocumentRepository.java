package com.wss.documentation.api.repository;

import com.wss.documentationCommon.entity.ItemDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemDocumentRepository extends JpaRepository<ItemDocumentEntity,String> {

    public List<ItemDocumentEntity> findAllByNameAndDocumentation_Id(String name, String documentation_id);
    public ItemDocumentEntity findFirstByNameAndDocumentation_Id(String name, String documentation_id);



}
