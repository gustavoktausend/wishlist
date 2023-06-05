package br.com.gustavokring.wishlist.api.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@With
@Value
@Builder
@Document(collection = "products")
public class ProductModel {

    @Id
    String id;
    @Field
    @Indexed(unique = true)
    String name;
    @Field
    String companyName;
    @Field
    BigDecimal price;



}
