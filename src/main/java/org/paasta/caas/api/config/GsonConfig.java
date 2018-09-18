package org.paasta.caas.api.config;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Gson bean config class (GsonBuilder, Converter for GsonBuilder)
 *
 * @author Hyungu Cho
 * @version 1.0
 * @since 2018.09.18
 */
@Configuration
public class GsonConfig {

    /**
     * Instantiates a new Gson builder bean (without another GsonBuilder).
     *
     * @return the gson builder
     */
    @Bean
    public GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

    /**
     * Instantiates a new Gson builder bean (with another GsonBuilder).
     *
     * @param builder the another gson builder
     * @return the gson builder
     */
    @Bean
    @Autowired
    public Gson gson(GsonBuilder builder) {
        return builder.create();
    }
}
