package br.com.gustavokring.wishlist.api.dto;

import br.com.gustavokring.wishlist.api.model.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

import static br.com.gustavokring.wishlist.api.util.SocialNumberUtils.PATTERN_MAIL;


@Value
@With
@JsonDeserialize(builder = ClientDTO.Builder.class)
@Builder(builderClassName = "Builder")
public class ClientDTO {

    String id;
    @NotEmpty
    String name;
    @NotEmpty
    @Pattern(regexp= PATTERN_MAIL)
    String email;
    List<ProductDTO> wishList;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {}



}
