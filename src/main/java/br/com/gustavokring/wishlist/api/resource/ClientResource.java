package br.com.gustavokring.wishlist.api.resource;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.facade.ClientFacade;
import br.com.gustavokring.wishlist.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;
    private final ClientFacade clientFacade;

    @GetMapping
    public Flux<ClientDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Mono<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
        return service.insert(clientDTO);
    }

    @GetMapping(path = "/{clientId}")
    public Mono<ClientDTO> findById(@PathVariable("clientId") String clientId) {
        return clientFacade.findById(clientId);
    }

    @GetMapping(path = "/{clientId}/wishlist", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ProductDTO> findWishlist(@PathVariable("clientId") String clientId) {
        return clientFacade.findWishListByClientId(clientId);
    }

    @PutMapping(path = "/{clientId}/wishlist")
    public Mono<ClientDTO> insertInClientWishList(@PathVariable("clientId") String clientId,
                                           @RequestBody ProductDTO productDTO) {
        return clientFacade.insertIntoWishlist(clientId, productDTO);

    }

}
