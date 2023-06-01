package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface ClientService {

    Mono<ClientDTO> insert(final ClientDTO clientModel);
    Mono<ClientDTO> update(final ClientDTO clientModel);
    Flux<ClientDTO> findAll();
    Mono<ClientDTO> findById(final String id);

}
