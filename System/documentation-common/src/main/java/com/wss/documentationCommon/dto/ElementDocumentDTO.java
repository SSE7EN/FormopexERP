package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

public class ElementDocumentDTO extends DocumentDTO{

    @JsonBackReference(value="elementMappingDTO-elementDocumentDTO")
    private List<ElementMappingDTO> elementMappings = new ArrayList<>();

    public List<ElementMappingDTO> getElementMappings() {
        return elementMappings;
    }

    public void setElementMappings(List<ElementMappingDTO> elementMappings) {
        this.elementMappings = elementMappings;
    }
}
