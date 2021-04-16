package com.wss.element.api.repository;

import com.wss.projectElementCommon.entity.ElementEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementRepository extends JpaRepository<ElementEntity, String> {


    @Override
    @EntityGraph(value = "element.withProject")
    ElementEntity save(ElementEntity s);

    Integer countAllByNameAndProject_Id(String name, String project_id);
}
