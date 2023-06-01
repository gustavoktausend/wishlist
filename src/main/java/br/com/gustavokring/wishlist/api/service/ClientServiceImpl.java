package br.com.gustavokring.wishlist.api.service;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.mapper.ClientMapper;
import br.com.gustavokring.wishlist.api.repository.ClientRepository;
import br.com.gustavokring.wishlist.api.service.exception.ClientNotFoundException;
import br.com.gustavokring.wishlist.api.service.exception.ProductNotFoundException;
import io.vavr.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public Mono<ClientDTO> insert(ClientDTO clientDTO) {
        return repository.insert(ClientMapper.parseTo(clientDTO))
                .map(ClientMapper::parseTo);
    }

    @Override
    public Mono<ClientDTO> update(ClientDTO clientDTO) {
        return repository.save(ClientMapper.parseTo(clientDTO))
                .map(ClientMapper::parseTo);
    }

    @Override
    public Flux<ClientDTO> findAll() {
        return repository.findAll()
                .map(ClientMapper::parseTo);
    }

    @Override
    public Mono<ClientDTO> findById(String id) {
        var messageError = Lazy.of(() -> MessageFormat.format("Cliente código {0} não encontrado", id));
        return repository.findById(id)
                .map(ClientMapper::parseTo)
                .onErrorMap(IllegalArgumentException.class::isInstance, throwable ->
                        new ClientNotFoundException(messageError.get())
                );
    }

}
