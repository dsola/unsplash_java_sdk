package com.unsplash.sdk.api.v1.resources;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserProfileDeserializer extends StdDeserializer<UserProfile> {

    public UserProfileDeserializer() {
        this(null);
    }

    private UserProfileDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UserProfile deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode productNode = jp.getCodec().readTree(jp);
        UserProfile userProfile = new UserProfile(
            extractTextValueFromNode(productNode, "id"),
            extractTextValueFromNode(productNode, "username"),
            extractTextValueFromNode(productNode, "first_name"),
            extractTextValueFromNode(productNode, "last_name"),
            convertToLocalDateTime(extractTextValueFromNode(productNode, "updated_at"))
        );

        if (productNode.has("bio")) {
            userProfile.setBio(extractTextValueFromNode(productNode, "bio"));
        }
        if (productNode.has("location")) {
            userProfile.setLocation(extractTextValueFromNode(productNode, "location"));
        }
        UserProfileMetrics metrics = new UserProfileMetrics(
            extractIntegerValueFromNode(productNode, "total_likes"),
            extractIntegerValueFromNode(productNode, "total_photos"),
            extractIntegerValueFromNode(productNode, "total_collections")
        );
        userProfile.setMetrics(metrics);

        return userProfile;
    }

    private String extractTextValueFromNode(JsonNode node, String property) {
        return node.get(property).textValue();
    }

    private Integer extractIntegerValueFromNode(JsonNode node, String property) {
        return node.get(property).intValue();
    }

    private LocalDateTime convertToLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
    }
}
