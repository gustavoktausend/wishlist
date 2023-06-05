package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.model.ProductModel;
import br.com.gustavokring.wishlist.api.repository.ProductRepository;
import br.com.gustavokring.wishlist.api.service.exception.ProductNotFoundException;
import io.vavr.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Mono<ProductModel> insert(ProductModel productModel) {
        return repository.save(productModel);
    }

    @Override
    public Flux<ProductModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<ProductModel> findById(String id) {
        var messageError = Lazy.of(() -> MessageFormat.format("Produto código {0} não encontrado", id));

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(messageError.get())))
                .onErrorMap(IllegalArgumentException.class::isInstance, throwable ->
                        new ProductNotFoundException(messageError.get())
                );
    }


}
