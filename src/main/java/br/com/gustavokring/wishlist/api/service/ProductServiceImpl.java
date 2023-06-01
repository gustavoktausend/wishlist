package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.dto.ProductFilterDTO;
import br.com.gustavokring.wishlist.api.exception.BusinesServiceException;
import br.com.gustavokring.wishlist.api.mapper.ProductMapper;
import br.com.gustavokring.wishlist.api.repository.ProductRepository;
import br.com.gustavokring.wishlist.api.service.exception.ProductNotFoundException;
import io.vavr.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.text.MessageFormat;

import static br.com.gustavokring.wishlist.api.exception.enumeration.ErrorApiCode.CLIENT_NOT_FOUND;
import static br.com.gustavokring.wishlist.api.helper.MessageHelper.getMessage;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Flux<ProductDTO> findByFilter(ProductFilterDTO filter) {
        return null;
    }

    @Override
    public Mono<ProductDTO> insert(ProductDTO productDTO) {
        return repository.insert(ProductMapper.parseTo(productDTO))
                .map(ProductMapper::parseTo);
    }

    @Override
    public Mono<ProductDTO> update(ProductDTO productDTO) {
        return repository.save(ProductMapper.parseTo(productDTO))
                .map(ProductMapper::parseTo);
    }

    @Override
    public Flux<ProductDTO> findAll() {
        return repository.findAll()
                .map(ProductMapper::parseTo);
    }

    @Override
    public Mono<ProductDTO> findById(String id) {
        var messageError = Lazy.of(() -> MessageFormat.format("Produto código {0} não encontrado", id));

        return repository.findById(id)
                .map(ProductMapper::parseTo)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(messageError.get())))
                .onErrorMap(IllegalArgumentException.class::isInstance, throwable ->
                        new ProductNotFoundException(messageError.get())
                );
    }


}
