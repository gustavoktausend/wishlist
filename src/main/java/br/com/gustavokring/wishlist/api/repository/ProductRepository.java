package br.com.gustavokring.wishlist.api.repository;

import br.com.gustavokring.wishlist.api.model.ProductModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductModel, String> {
}
