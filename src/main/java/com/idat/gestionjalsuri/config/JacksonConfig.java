package com.idat.gestionjalsuri.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer(){
        return builder->{
            builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            builder.defaultViewInclusion(true);
        };
    }
}
