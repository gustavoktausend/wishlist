package br.com.gustavokring.wishlist.api.facade;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.model.ClientModel;
import br.com.gustavokring.wishlist.api.service.ClientService;
import br.com.gustavokring.wishlist.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

@Slf4j
public class ClientFacadeTest {

    @InjectMocks
    private ClientService clientService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    ClientDTO client1 = ClientDTO.builder()
            .name("client1")
            .email("client@email.com")
            .build();

    ClientDTO anotherClient1 = ClientDTO.builder()
            .name("client1")
            .email("client@email.com")
            .build();

    ClientDTO client2 = ClientDTO.builder()
            .name("client2")
            .email("client2@email.com")
            .build();

    ProductDTO product1 = ProductDTO.builder()
            .name("produto1")
            .companyName("empresa1")
            .build();


}
