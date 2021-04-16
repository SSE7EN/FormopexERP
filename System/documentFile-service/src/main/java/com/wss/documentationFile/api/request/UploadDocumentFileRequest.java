package com.wss.documentationFile.api.request;

import javax.validation.constraints.NotNull;

public class UploadDocumentFileRequest {

    @NotNull(message = "documentId cannot be null")
    public String documentId;


}
