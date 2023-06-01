package br.com.gustavokring.wishlist.api.mapper;

import br.com.gustavokring.wishlist.api.dto.ProductDTO;
import br.com.gustavokring.wishlist.api.model.ProductModel;

public final class ProductMapper {

    public static ProductModel parseTo(final ProductDTO productDTO) {
        return ProductModel.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .companyName(productDTO.getCompanyName())
                .build();
    }

    public static ProductDTO parseTo(final ProductModel productModel) {
        return ProductDTO.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .companyName(productModel.getCompanyName())
                .build();
    }



}
