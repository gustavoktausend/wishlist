package br.com.gustavokring.wishlist.api.exception.enumeration;

import br.com.gustavokring.wishlist.api.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorApiCode implements ErrorCode {

    UNKNOWN_INTERNAL_SERVER_ERROR("messages.error.unknown.internal-server-error"),
    CLIENT_NOT_FOUND("messages.error.client.not-found"),
    CLIENT_ALREADY_EXISTS("messages.error.client.allready-exists"),
    PRODUCT_NOT_FOUND_INVALID_ID("messages.error.product.not-found.invalid.id"),
    PRODUCT_NOT_FOUND("messages.error.product.not-found"),
    INVALID_PRODUCT("messages.error.invalid.product"),
    EMPTY_WISHLIST("messages.error.wishlist.empty"),
    FULL_WISHLIST("messages.error.wishlist.full"),
    WISHLIST_CLIENT_NOT_FOUND("messages.error.wishlist.client.not-found"),
    WISHLIST_PRODUCT_NOT_FOUND("messages.error.wishlist.product.not-found");




    public final String messageKey;
}
