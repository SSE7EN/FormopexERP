package com.wss.project.service;

import com.wss.projectElementCommon.dto.ProjectDTO;
import com.wss.projectElementCommon.dto.ProjectWithElementsDTO;
import com.wss.projectElementCommon.dto.ProjectWithItemListsDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Project service interface
 *
 * @author se7en
 */
@Service
public interface IProjectService  {

    /**
     * Save project
     * @param projectDTO
     * @return
     */
    public ProjectDTO insert(ProjectDTO projectDTO);

    /**
     * Update project
     * @param projectDTO
     * @return
     */
    public ProjectDTO update(ProjectDTO projectDTO);

    /**
     * Get all projects
     * @return
     */
    public List<ProjectDTO> getAll();

    /**
     * Get an project by id
     * @param id
     * @return
     */
    public <T> T findById(String id, Type type);



    /**
     * Delete a project
     * @param id
     */
    public void delete(String id);
}
