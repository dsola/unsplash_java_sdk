package com.unsplash.sdk.api.v1.resources.profile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserProfileDeserializer extends StdDeserializer<UserProfileV1> {

    public UserProfileDeserializer() {
        this(null);
    }

    private UserProfileDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UserProfileV1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode productNode = jp.getCodec().readTree(jp);
        return UserProfileV1Builder.fromJsonNode(productNode);
    }
}
