package br.com.gustavokring.wishlist.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@Value
@With
@Builder
public class ProductFilterDTO {

    Long productId;
    String name;
    BigDecimal price;

}
