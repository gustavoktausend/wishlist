package br.com.gustavokring.wishlist.api.resource;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.facade.ClientFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientFacade clientFacade;

    @Operation(summary = "Listagem de Clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os registros de clientes cadastrados")
    })
    @GetMapping
    public Flux<ClientDTO> getAll() {
        return clientFacade.findAll();
    }

    @Operation(summary = "Cadastro de Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Cliente já registrado email é usado como identificador unico")
    })
    @PostMapping
    public Mono<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
        return clientFacade.insert(clientDTO);
    }

    @Operation(summary = "Consulta de Cliente por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente consultado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping(path = "/{clientId}")
    public Mono<ClientDTO> findById(@PathVariable("clientId") String clientId) {
        return clientFacade.findById(clientId);
    }

}
