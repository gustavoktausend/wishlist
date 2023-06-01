package br.com.gustavokring.wishlist.api.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@With
@Value
@Builder
@Document(collection = "clients")
public class ClientModel {

    @Id
    String id;
    String name;
    @Indexed(unique = true)
    String email;
    List<ProductModel> wishList;


}
