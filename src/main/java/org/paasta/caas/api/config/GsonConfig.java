package org.paasta.caas.api.config;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfig {
    @Bean
    public GsonBuilder gsonBuilder() {
        return new GsonBuilder();
            //.setDateFormat( "yyyy-MM-dd HH:mm:ss" );
            //.registerTypeAdapter( LocalDateTime.class, new LocalDateTimeConverter() );
    }

    @Bean
    @Autowired
    public Gson gson(GsonBuilder builder) {
        return builder.create();
    }

    public static class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss'Z'" ).withZone( ZoneId.of( "UTC" ) );

        private static final DateTimeFormatter serializeDateTimeFormatter =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );

        @Override
        public JsonElement serialize ( LocalDateTime src, Type typeOfSrc, JsonSerializationContext context ) {
            JsonElement element = new JsonPrimitive( src.format( serializeDateTimeFormatter ) );

            return element;
        }

        @Override
        public LocalDateTime deserialize ( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
            final String timeString = json.getAsString();
            LocalDateTime time = LocalDateTime.from( dateTimeFormatter.parse( timeString ) );

            return time;
        }
    }
}
