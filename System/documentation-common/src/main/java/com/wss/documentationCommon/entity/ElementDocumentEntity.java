package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("elementDocument")
public class ElementDocumentEntity extends DocumentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToMany
    @JoinTable(
            name = "element_document_element_mapping",
            joinColumns = @JoinColumn(name = "element_mapping_id"),
            inverseJoinColumns = @JoinColumn(name = "element_document_id"))
    List<ElementMappingEntity> elementMappings;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ElementMappingEntity> getElementMappings() {
        return elementMappings;
    }

    public void setElementMappings(List<ElementMappingEntity> elementMappings) {
        this.elementMappings = elementMappings;
    }
}
