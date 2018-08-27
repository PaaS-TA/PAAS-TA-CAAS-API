package org.paasta.caas.api.common;

import com.google.gson.*;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class GsonDeserializer implements JsonDeserializer<Integer> ,JsonSerializer<Integer> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GsonDeserializer.class);

    @Override
    public JsonElement serialize ( Integer src, Type typeOfSrc, JsonSerializationContext context ) {

        JsonElement element = new JsonPrimitive(src.toString());

        return element;
    }

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return json.getAsJsonPrimitive().getAsInt();
    }

}
