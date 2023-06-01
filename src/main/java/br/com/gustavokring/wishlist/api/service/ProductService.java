package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.dto.ProductFilterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface ProductService {

    Flux<ProductDTO> findByFilter(final ProductFilterDTO filter);
    Mono<ProductDTO> insert(final ProductDTO productModel);
    Mono<ProductDTO> update(final ProductDTO productModel);
    Flux<ProductDTO> findAll();
    Mono<ProductDTO> findById(final String id);

}
