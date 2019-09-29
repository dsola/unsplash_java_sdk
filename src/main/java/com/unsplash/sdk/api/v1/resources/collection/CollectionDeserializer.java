package com.unsplash.sdk.api.v1.resources.collection;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.unsplash.sdk.api.v1.resources.profile.UserProfileV1Builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CollectionDeserializer extends StdDeserializer<CollectionV1> {
    public CollectionDeserializer() {
        this(null);
    }

    private CollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CollectionV1 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode productNode = jp.getCodec().readTree(jp);
        CollectionV1 collection = new CollectionV1(extractIntegerValueFromNode(productNode, "id"));

        collection.setTitle(extractTextValueFromNode(productNode, "title"));
        collection.setDescription(extractTextValueFromNode(productNode, "description"));
        collection.setCurated(extractBooleanFromNode(productNode, "curated"));
        collection.setFeatured(extractBooleanFromNode(productNode, "featured"));
        collection.setTotalPhotos(extractIntegerValueFromNode(productNode, "total_photos"));
        collection.setPrivate(extractBooleanFromNode(productNode, "private"));
        collection.setUpdatedDate(extractDateTimeFromNode(productNode, "updated_at"));
        collection.setPublishedDate(extractDateTimeFromNode(productNode, "published_at"));
        collection.setUser(UserProfileV1Builder.fromJsonNode(productNode.get("user")));

        return collection;
    }

    private String extractTextValueFromNode(JsonNode node, String property) {
        return node.get(property).textValue();
    }

    private Boolean extractBooleanFromNode(JsonNode node, String property) {
        return node.get(property).booleanValue();
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

