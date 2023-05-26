package br.com.carbigdata.customer.api.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.time.Duration;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig {

    @Value("${spring.r2dbc.database}")
    private String database;
    @Value("${spring.r2dbc.schema}")
    private String schema;
    @Value("${spring.r2dbc.host}")
    private String host;
    @Value("${spring.r2dbc.port:5432}")
    private Integer port;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;

    @Value("${spring.r2dbc.pool.max-idle-time}")
    private Long maxIdleTime;
    @Value("${spring.r2dbc.pool.initial-size}")
    private Integer initialSize;
    @Value("${spring.r2dbc.pool.max-size}")
    private Integer maxSize;
    @Value("${spring.r2dbc.pool.validation-query}")
    private String validationQuery;

    @Bean
    @Primary
    ConnectionFactory customerConnectionFactory() {
        var connectionFactory = new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .database(database)
                        .schema(schema)
                        .host(host)
                        .port(port)
                        .username(username)
                        .password(password)
                        .build()
        );

        var poolConfiguration = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMinutes(maxIdleTime))
                .initialSize(initialSize)
                .maxSize(maxSize)
                .validationQuery(validationQuery)
                .build();

        return new ConnectionPool(poolConfiguration);
    }

    @Bean
    ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory connectionFactory ) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    TransactionalOperator transactionalOperator(ReactiveTransactionManager transactionManager) {
        return TransactionalOperator.create(transactionManager);
    }
}

