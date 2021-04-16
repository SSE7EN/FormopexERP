package com.wss.common.exception;

import com.wss.common.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


public class RequestException extends ResponseStatusException {

    private static final long serialVersionUID = -2217466109027636423L;

    public RequestException(HttpStatus status, String reason) {
        super(status, reason);
    }

}
