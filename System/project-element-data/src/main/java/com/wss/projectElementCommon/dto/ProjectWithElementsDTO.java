package com.wss.projectElementCommon.dto;

import java.util.ArrayList;
import java.util.List;

public class ProjectWithElementsDTO extends ProjectDTO {

    /**
     *
     * @return elements
     */

    public List<ElementDTO> getElements() {
        return elements;
    }

    /**
     *
     * @param elements to the elements list
     */
    public void setElements(List<ElementDTO> elements) {
        this.elements = elements;
    }

    /**
     * List of elements
     */
    private List<ElementDTO> elements = new ArrayList<ElementDTO>();
}
