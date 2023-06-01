package br.com.gustavokring.wishlist.api.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;

@With
@Value
@Builder
@Document(collection = "products")
@CompoundIndex(def = "{'name': 1, 'companyName': 1}", unique = true)
public class ProductModel {

    @Id
    String id;
    @Indexed(unique = true)
    String name;
    String companyName;
    BigDecimal price;



}
