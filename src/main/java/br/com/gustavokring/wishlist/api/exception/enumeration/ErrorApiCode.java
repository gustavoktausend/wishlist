package br.com.gustavokring.wishlist.api.exception.enumeration;

import br.com.gustavokring.wishlist.api.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorApiCode implements ErrorCode {

    UNKNOWN_INTERNAL_SERVER_ERROR("messages.error.unknown.internal-server-error"),
    CLIENT_NOT_FOUND("messages.error.client.not-found"),
    PRODUCT_NOT_FOUND_INVALID_ID("messages.error.product.not-found.invalid.id"),
    PRODUCT_NOT_FOUND("messages.error.product.not-found"),
    INVALID_PRODUCT("messages.error.invalid.product");



    public final String messageKey;
}
