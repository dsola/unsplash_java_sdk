package com.unsplash.sdk.api.v1.resources.photo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.unsplash.sdk.api.v1.resources.profile.UserProfileV1Builder;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PhotoDeserializer extends StdDeserializer<PhotoV1> {
    public PhotoDeserializer() {
        this(null);
    }

    private PhotoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PhotoV1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode productNode = jp.getCodec().readTree(jp);
        PhotoV1 photo = new PhotoV1(extractTextValueFromNode(productNode, "id"));

        photo.setWidth(extractIntegerValueFromNode(productNode, "width"));
        photo.setHeight(extractIntegerValueFromNode(productNode, "height"));
        photo.setColor(Color.decode(extractTextValueFromNode(productNode, "color")));
        photo.setUpdatedDate(extractDateTimeFromNode(productNode, "updated_at"));
        photo.setPublishedDate(extractDateTimeFromNode(productNode, "created_at"));
        photo.setDescription(extractTextValueFromNode(productNode, "description"));
        photo.setTotalLikes(extractIntegerValueFromNode(productNode, "likes"));
        photo.setUser(UserProfileV1Builder.fromJsonNode(productNode.get("user")));

        JsonNode urlsNode = productNode.get("urls");
        PhotoUrls photoUrls = new PhotoUrls(
            new URL(extractTextValueFromNode(urlsNode, "regular")),
            new URL(extractTextValueFromNode(urlsNode, "full")),
            new URL(extractTextValueFromNode(urlsNode, "small")),
            new URL(extractTextValueFromNode(urlsNode, "thumb")),
            new URL(extractTextValueFromNode(urlsNode, "raw"))
        );
        photo.setPhotoUrls(photoUrls);

        return photo;
    }

    private String extractTextValueFromNode(JsonNode node, String property) {
        return node.get(property).textValue();
    }

    private Integer extractIntegerValueFromNode(JsonNode node, String property) {
        return node.get(property).intValue();
    }

    private LocalDateTime extractDateTimeFromNode(JsonNode node, String property) {
        return convertToLocalDateTime(extractTextValueFromNode(node, property));
    }

    private LocalDateTime convertToLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
    }
}
