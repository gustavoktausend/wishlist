package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.model.ClientModel;
import br.com.gustavokring.wishlist.api.repository.ClientRepository;
import br.com.gustavokring.wishlist.api.service.exception.ClientNotFoundException;
import io.vavr.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public Mono<ClientModel> insert(ClientModel clientModel) {
        return repository.save(clientModel);
    }

    @Override
    public Mono<ClientModel> update(ClientModel clientModel) {
        return repository.save(clientModel);
    }

    @Override
    public Flux<ClientModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<ClientModel> findById(String id) {
        var messageError = Lazy.of(() -> MessageFormat.format("Cliente código {0} não encontrado", id));
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ClientNotFoundException(messageError.get())))
                .onErrorMap(IllegalArgumentException.class::isInstance, throwable ->
                        new ClientNotFoundException(messageError.get())
                );
    }

}
