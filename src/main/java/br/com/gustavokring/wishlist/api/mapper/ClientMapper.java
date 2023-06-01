package br.com.gustavokring.wishlist.api.mapper;

import br.com.gustavokring.wishlist.api.dto.ClientDTO;
import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.model.ClientModel;
import br.com.gustavokring.wishlist.api.model.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public final class ClientMapper {

    public static ClientModel parseTo(final ClientDTO clientDTO) {

        List<ProductModel> wishList = List.of();

        if(nonNull(clientDTO.getWishList()))
            wishList = clientDTO.getWishList()
                    .stream()
                    .map(ProductMapper::parseTo)
                    .collect(Collectors.toList());

        return ClientModel.builder()
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .wishList(wishList)
                .build();
    }

    public static ClientDTO parseTo(final ClientModel clientModel) {

        List<ProductDTO> wishList = List.of();

        if(nonNull(clientModel.getWishList()))
            wishList = clientModel.getWishList()
                    .stream()
                    .map(ProductMapper::parseTo)
                    .collect(Collectors.toList());

        return ClientDTO.builder()
                .id(clientModel.getId())
                .name(clientModel.getName())
                .email(clientModel.getEmail())
                .wishList(wishList)
                .build();
    }



}
