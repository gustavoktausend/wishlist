package br.com.gustavokring.wishlist.api.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@RequiredArgsConstructor
@EnableReactiveMongoRepositories(basePackages = "br.com.gustavokring.wishlist.api.repository")
public class RepositoryConfig {

    @Getter
    @Value("${spring.data.mongodb.host}")
    private String host;

    @Getter
    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Getter
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Getter
    @Value("${spring.data.mongodb.username}")
    private String username;

    @Getter
    @Value("${spring.data.mongodb.password}")
    private String password;

    @Getter
    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }

}
