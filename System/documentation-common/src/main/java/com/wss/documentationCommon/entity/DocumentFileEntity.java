package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "documentation_file")
public class DocumentFileEntity  implements Serializable {

    private final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;


    /**
     * Path
     */
    @Column(name = "path", nullable = false)
    private String path;


    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
