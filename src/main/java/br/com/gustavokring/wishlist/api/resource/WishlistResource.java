package br.com.gustavokring.wishlist.api.resource;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.facade.ClientFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistResource {

    private final ClientFacade clientFacade;

    @Operation(summary = "Inserir produto na wishlist do Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente alterado"),
            @ApiResponse(responseCode = "400", description = "Produto válido não encontrado"),
            @ApiResponse(responseCode = "400", description = "Cliente válido não encontrado"),
            @ApiResponse(responseCode = "412", description = "Wishlist cheia")

    })
    @PatchMapping(path = "/{clientId}/insert")
    public Mono<ClientDTO> insertInWishlist(@PathVariable("clientId") String clientId,
                                                  @RequestBody ProductDTO productDTO) {
        return clientFacade.insertIntoWishlist(clientId, productDTO);
    }

    @Operation(summary = "Remover produto da wishlist do Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente alterado"),
            @ApiResponse(responseCode = "400", description = "Produto válido não encontrado"),
            @ApiResponse(responseCode = "400", description = "Cliente válido não encontrado"),
            @ApiResponse(responseCode = "412", description = "Wishlist vazia")

    })
    @PatchMapping(path = "/{clientId}/remove")
    public Mono<ClientDTO> removeFromWishlist(@PathVariable("clientId") String clientId,
                                                  @RequestBody ProductDTO productDTO) {
        return clientFacade.removeFromWishlist(clientId, productDTO);
    }

    @Operation(summary = "Busca de produtos por id na wishlist do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna wishlist filtrada"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping(path = "/{clientId}")
    public Mono<List<ProductDTO>> getWishlist(@PathVariable("clientId") String clientId,
                                              @RequestParam(required = false) String productId) {
        return clientFacade.findProductFromWishlistByFilter(clientId, productId);
    }


}
