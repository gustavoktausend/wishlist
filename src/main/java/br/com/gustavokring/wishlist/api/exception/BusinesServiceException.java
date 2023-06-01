package br.com.gustavokring.wishlist.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinesServiceException extends ResponseStatusException {

    public BusinesServiceException(HttpStatus status) {
        super(status);
    }

    public BusinesServiceException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BusinesServiceException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
