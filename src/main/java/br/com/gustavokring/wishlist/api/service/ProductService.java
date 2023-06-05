package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.model.ProductModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {

    Mono<ProductModel> insert(final ProductModel productModel);
    Flux<ProductModel> findAll();
    Mono<ProductModel> findById(final String id);

}
