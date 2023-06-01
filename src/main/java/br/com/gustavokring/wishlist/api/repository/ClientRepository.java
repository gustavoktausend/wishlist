package br.com.gustavokring.wishlist.api.repository;

import br.com.gustavokring.wishlist.api.model.ClientModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends ReactiveMongoRepository<ClientModel, String> {
}
