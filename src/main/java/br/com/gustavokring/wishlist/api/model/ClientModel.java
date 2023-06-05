package br.com.gustavokring.wishlist.api.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@With
@Value
@Builder
@Document(collection = "clients")
public class ClientModel {

    @Id
    String id;
    @Field
    String name;

    @Indexed(unique = true)
    @Field
    String email;

    @Field
    Set<String> wishListIds;

    @Transient
    List<ProductModel> wishList;

    public ClientModel(String id,
                       String name,
                       String email,
                       Set<String> wishListIds,
                       @org.springframework.beans.factory.annotation.Value("null") List<ProductModel> wishList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.wishListIds = wishListIds;
        this.wishList = wishList;
    }
}
