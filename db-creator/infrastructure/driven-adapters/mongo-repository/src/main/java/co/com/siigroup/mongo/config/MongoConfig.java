package co.com.siigroup.mongo.config;

import co.com.siigroup.mongo.config.MongoDBSecret;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    public MongoDBSecret dbSecret(Environment env) {
        return MongoDBSecret.builder()
                .uri("mongodb://localhost:27017/test")
                .build();
    }

    @Bean
    public ReactiveMongoClientFactory mongoProperties(MongoDBSecret secret, Environment env) {
        MongoProperties properties = new MongoProperties();
        properties.setUri(secret.getUri());

        List<MongoClientSettingsBuilderCustomizer> list = new ArrayList<>();
        list.add(new MongoPropertiesClientSettingsBuilderCustomizer(properties, env));
        return new ReactiveMongoClientFactory(list);
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoProperties) {
        return new ReactiveMongoTemplate(mongoProperties, "estudiante");
    }
}
