package com.wss.project.repository;

import com.wss.projectElementCommon.entity.ProjectEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<ProjectEntity, String> {


    List<ProjectEntity> findAll();

    @Override
    @EntityGraph(value = "project.withElements")
    Optional<ProjectEntity> findById(String id);



}
