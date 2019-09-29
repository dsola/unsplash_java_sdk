package com.unsplash.sdk.api.v1.resources.profile;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserProfileV1Builder {
    public static UserProfileV1 fromJsonNode(JsonNode productNode) throws MalformedURLException {
        UserProfileV1 userProfile = new UserProfileV1(
                extractTextValueFromNode(productNode, "id"),
                extractTextValueFromNode(productNode, "username"),
                extractTextValueFromNode(productNode, "first_name"),
                extractTextValueFromNode(productNode, "last_name"),
                extractDateTimeFromNode(productNode)
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

    private static String extractTextValueFromNode(JsonNode node, String property) {
        return node.get(property).textValue();
    }

    private static Integer extractIntegerValueFromNode(JsonNode node, String property) {
        return node.get(property).intValue();
    }

    private static LocalDateTime extractDateTimeFromNode(JsonNode node) {
        return convertToLocalDateTime(extractTextValueFromNode(node, "updated_at"));
    }

    private static LocalDateTime convertToLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
    }
}
