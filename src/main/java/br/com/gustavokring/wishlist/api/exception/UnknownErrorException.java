package br.com.gustavokring.wishlist.api.exception;

public class UnknownErrorException extends RuntimeException {

    public UnknownErrorException() {
    }

    public UnknownErrorException(String message) {
        super(message);
    }

    public UnknownErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownErrorException(Throwable cause) {
        super(cause);
    }

    public UnknownErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
