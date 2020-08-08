package com.reactive.reactive.todo.list.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
class R2DBCConfiguration extends AbstractR2dbcConfiguration {
    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("mem:todo-list;DB_CLOSE_DELAY=-1;")
                        .username("sa")
                        .build()
        );
    }

    @Bean
    public ConnectionFactoryInitializer initializer(H2ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/migration/initial-data.sql")));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}