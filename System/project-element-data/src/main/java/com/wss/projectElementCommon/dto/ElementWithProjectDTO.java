package com.wss.projectElementCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ElementWithProjectDTO extends ElementDTO {
    /**
     * Project
     */
    private ProjectDTO project;

    /**
     *
     * @return project
     */
    public ProjectDTO getProject() {
        return project;
    }

    /**
     *
     * @param projectDTO
     */
    public void setProject(ProjectDTO projectDTO) {
        this.project = projectDTO;
    }
}
