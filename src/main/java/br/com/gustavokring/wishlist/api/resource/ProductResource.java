package br.com.gustavokring.wishlist.api.resource;

import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getAll() {
        return productService.findAll();
    }

    @PostMapping
    public Mono<ProductDTO> insert(@RequestBody ProductDTO productDTO) {
        return productService.insert(productDTO);
    }



}
