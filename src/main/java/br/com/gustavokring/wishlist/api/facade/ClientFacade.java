package br.com.gustavokring.wishlist.api.facade;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.exception.BusinesServiceException;
import br.com.gustavokring.wishlist.api.mapper.ClientMapper;
import br.com.gustavokring.wishlist.api.model.ClientModel;
import br.com.gustavokring.wishlist.api.service.ClientService;
import br.com.gustavokring.wishlist.api.service.ProductService;
import br.com.gustavokring.wishlist.api.service.exception.ClientNotFoundException;
import br.com.gustavokring.wishlist.api.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.gustavokring.wishlist.api.exception.enumeration.ErrorApiCode.*;
import static br.com.gustavokring.wishlist.api.helper.MessageHelper.getMessage;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.function.Predicate.not;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;
    private final ProductService productService;

    public Mono<ClientDTO> insert(final ClientDTO clientDTO ) {
        return clientService.insert(ClientMapper.parseTo(clientDTO))
                .onErrorMap(DuplicateKeyException.class::isInstance, throwable ->
                        new BusinesServiceException(BAD_REQUEST, getMessage(CLIENT_ALREADY_EXISTS, clientDTO.getEmail())))
                .map(ClientMapper::parseTo);
    }

    public Flux<ClientDTO> findAll() {
        return clientService.findAll()
                .map(ClientMapper::parseTo);
    }

    public Mono<ClientDTO> insertIntoWishlist(final String clientId, final ProductDTO productDTO) {
        if(isNull(productDTO.getId()))
            return Mono.error(new BusinesServiceException(BAD_REQUEST, getMessage(PRODUCT_NOT_FOUND_INVALID_ID)));

        return clientService.findById(clientId)
                .flatMap(clientModel -> {
                    if(nonNull(clientModel.getWishListIds()) && clientModel.getWishListIds().size() >= 20 )
                        return Mono.error(new BusinesServiceException(PRECONDITION_FAILED, getMessage(FULL_WISHLIST)));

                    return productService.findById(productDTO.getId())
                            .zipWith(Mono.just(clientModel));
                })
                .flatMap(tuple2 -> {
                    var product = tuple2.getT1();
                    var client = tuple2.getT2();
                    var wishListIds = client.getWishListIds();

                    var set = new HashSet<String>();

                    set.add(product.getId());

                    if(nonNull(wishListIds))
                        set.addAll(wishListIds);

                    return clientService.update(client.withWishListIds(set));
                })
                .map(ClientMapper::parseTo)
                .onErrorMap(ClientNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(BAD_REQUEST, getMessage(WISHLIST_CLIENT_NOT_FOUND, clientId))
                )
                .onErrorMap(ProductNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(BAD_REQUEST, getMessage(WISHLIST_PRODUCT_NOT_FOUND, productDTO.getId()))
                )
                .onErrorMap(not(BusinesServiceException.class::isInstance), throwable ->
                        new BusinesServiceException(INTERNAL_SERVER_ERROR, getMessage(UNKNOWN_INTERNAL_SERVER_ERROR))
                );

    }

    public Mono<ClientDTO> removeFromWishlist(final String clientId, final ProductDTO productDTO) {
        if(isNull(productDTO.getId()))
            return Mono.error(new BusinesServiceException(BAD_REQUEST, getMessage(PRODUCT_NOT_FOUND_INVALID_ID)));

        return clientService.findById(clientId)
                .flatMap(clientModel -> {
                    if(isNull(clientModel.getWishListIds()) || clientModel.getWishListIds().isEmpty())
                        return Mono.error(new BusinesServiceException(PRECONDITION_FAILED, getMessage(EMPTY_WISHLIST)));

                    return productService.findById(productDTO.getId())
                            .zipWith(Mono.just(clientModel));
                })
                .flatMap(tuple2 -> {
                    var product = tuple2.getT1();
                    var client = tuple2.getT2();
                    var wishListIds = client.getWishListIds();

                    if(nonNull(wishListIds))
                        wishListIds.remove(product.getId());

                    return clientService.update(client.withWishListIds(wishListIds));
                })
                .map(ClientMapper::parseTo)
                .onErrorMap(ClientNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(BAD_REQUEST, getMessage(WISHLIST_CLIENT_NOT_FOUND, clientId))
                )
                .onErrorMap(ProductNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(BAD_REQUEST, getMessage(WISHLIST_PRODUCT_NOT_FOUND, productDTO.getId()))
                )
                .onErrorMap(not(BusinesServiceException.class::isInstance), throwable ->
                        new BusinesServiceException(INTERNAL_SERVER_ERROR, getMessage(UNKNOWN_INTERNAL_SERVER_ERROR))
                );

    }

    public Mono<List<ProductDTO>> findProductFromWishlistByFilter(final String clientId, final String productId) {
        return findEntityById(clientId)
                .filter(clientModel -> nonNull(clientModel.getWishList()) && !clientModel.getWishList().isEmpty())
                .map(ClientMapper::parseTo)
                .map(ClientDTO::getWishList)
                .map(productDTOS -> {
                    if(nonNull(productId))
                        return productDTOS.stream()
                                .filter(productModel -> Objects.equals(productModel.getId(), productId))
                                .collect(Collectors.toList());
                    return productDTOS;
                })
                .switchIfEmpty(Mono.error(new BusinesServiceException(NOT_FOUND, getMessage(EMPTY_WISHLIST))));

    }

    public Mono<ClientDTO> findById(final String clientId) {
        return findEntityById(clientId)
                .map(ClientMapper::parseTo);
    }

    private Mono<ClientModel> findEntityById(final String clientId) {
        return clientService.findById(clientId)
                .flatMap(clientModel -> {
                    if(isNull(clientModel.getWishListIds()) || clientModel.getWishListIds().isEmpty())
                        return Mono.just(clientModel);

                    return Flux.fromIterable(clientModel.getWishListIds())
                            .flatMap(productService::findById)
                            .subscribeOn(Schedulers.boundedElastic())
                            .collectList()
                            .map(clientModel::withWishList);
                })
                .onErrorMap(ClientNotFoundException.class::isInstance, throwable ->
                        new BusinesServiceException(NOT_FOUND, getMessage(CLIENT_NOT_FOUND, clientId)));
    }


}
