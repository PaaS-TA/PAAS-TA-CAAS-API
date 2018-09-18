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

    /**
     * LocalDateTimeConverter class (for Gson builder)
     *
     * @author Hyungu Cho
     * @version 1.0
     * @since 2018.08.16
     */
    public static class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss'Z'" ).withZone( ZoneId.of( "UTC" ) );

        private static final DateTimeFormatter serializeDateTimeFormatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

        /**
         * Serialize JsonElement for LocalDateTime
         *
         * @param src       the local date time source
         * @param typeOfSrc the type of json source
         * @param context   the json serialization context
         * @return the converted json element
         */
        @Override
        public JsonElement serialize ( LocalDateTime src, Type typeOfSrc, JsonSerializationContext context ) {
            JsonElement element = new JsonPrimitive( src.format( serializeDateTimeFormatter ) );

            return element;
        }

        /**
         * Deserialize JsonElement for LocalDateTime
         *
         * @param json    the json element
         * @param typeOfT the type of json
         * @param context the json deserialization context
         * @return the converted LocalDateTime
         * @throws JsonParseException
         */
        @Override
        public LocalDateTime deserialize ( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
            final String timeString = json.getAsString();
            LocalDateTime time = LocalDateTime.from( dateTimeFormatter.parse( timeString ) );

            return time;
        }
    }
}
