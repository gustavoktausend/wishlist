package br.com.gustavokring.wishlist.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    private final ApplicationContext context;

    @Bean
    OpenAPI entityOpenAPI(@Value("${swagger.title}") String title,
                          @Value("${swagger.description}") String description,
                          @Value("${swagger.version}") String version) {

        var servers = new ArrayList<Server>();

        return new OpenAPI()
                .servers(servers)
                .info(new Info()
                        .title(StringUtils.capitalize(title))
                        .description(description)
                        .version(version)
                );
    }
}
