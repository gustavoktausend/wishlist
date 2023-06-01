package br.com.gustavokring.wishlist.api.facade;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.exception.BusinesServiceException;
import br.com.gustavokring.wishlist.api.service.ClientService;
import br.com.gustavokring.wishlist.api.service.ProductService;
import br.com.gustavokring.wishlist.api.service.exception.ClientNotFoundException;
import br.com.gustavokring.wishlist.api.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigInteger;
import java.util.List;

import static br.com.gustavokring.wishlist.api.exception.enumeration.ErrorApiCode.*;
import static br.com.gustavokring.wishlist.api.helper.MessageHelper.getMessage;
import static java.util.Objects.isNull;
import static java.util.function.Predicate.not;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;
    private final ProductService productService;

    public Flux<ProductDTO> findWishListByClientId(String clientId) {
        return clientService.findById(clientId)
                .flatMapMany(clientDTO -> Flux.fromIterable(clientDTO.getWishList()))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorMap(IllegalArgumentException.class::isInstance, throwable ->
                        new BusinesServiceException(NOT_FOUND, getMessage(CLIENT_NOT_FOUND, clientId))
                );

    }

    public Mono<ClientDTO> insertIntoWishlist(final String clientId, final ProductDTO productDTO) {

        if(isNull(productDTO.getId()))
            return Mono.error(new BusinesServiceException(NOT_FOUND, getMessage(PRODUCT_NOT_FOUND_INVALID_ID)));

        return clientService.findById(clientId)
                .filter(clientDTO -> !clientDTO.wishListIsFull())
                .flatMap(clientDTO ->
                    productService.findById(productDTO.getId())
                        .map(productForWishList -> {
                            clientDTO.getWishList().add(productForWishList);
                            return clientDTO;
                        })
                )
                .flatMap(clientService::update)
                .onErrorMap(ClientNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(PRECONDITION_FAILED, getMessage(PRODUCT_NOT_FOUND, productDTO.getId()))
                )
                .onErrorMap(ProductNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(PRECONDITION_FAILED, throwable.getMessage())
                )
                .onErrorMap(not(BusinesServiceException.class::isInstance), throwable ->
                        new BusinesServiceException(INTERNAL_SERVER_ERROR, getMessage(UNKNOWN_INTERNAL_SERVER_ERROR))
                );

    }

    public Mono<ClientDTO> findById(final String clientId) {
        return clientService.findById(clientId)
                .onErrorMap(ClientNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(NOT_FOUND, getMessage(CLIENT_NOT_FOUND, clientId)));
    }


}
