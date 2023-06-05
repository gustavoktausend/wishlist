package br.com.gustavokring.wishlist.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@Value
@With
@JsonDeserialize(builder = ProductDTO.Builder.class)
@Builder(builderClassName = "Builder")
public class ProductDTO {

    String id;
    String name;
    String companyName;
    BigDecimal price;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {}


}
