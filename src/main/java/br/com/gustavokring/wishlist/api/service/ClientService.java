package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.model.ClientModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface ClientService {

    Mono<ClientModel> insert(final ClientModel clientModel);
    Mono<ClientModel> update(final ClientModel clientModel);
    Flux<ClientModel> findAll();
    Mono<ClientModel> findById(final String id);

}
