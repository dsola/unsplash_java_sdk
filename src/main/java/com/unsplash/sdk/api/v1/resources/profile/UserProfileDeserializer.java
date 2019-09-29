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
        UserProfileV1 userProfile = new UserProfileV1(
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
        UserProfilePicture profilePicture = new UserProfilePicture(
            extractTextValueFromNode(productNode.get("profile_image"), "small"),
            extractTextValueFromNode(productNode.get("profile_image"), "medium"),
            extractTextValueFromNode(productNode.get("profile_image"), "large")
        );
        userProfile.setProfilePicture(profilePicture);

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
