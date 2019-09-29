package com.unsplash.sdk.api;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.base.CaseFormat;

public class UriFormat {
    private StringBuilder builder = new StringBuilder();

    @JsonAnySetter
    public void addToUri(String name, Object property) {
        if (builder.length() > 0) {
            builder.append("&");
        }
        String snakeCaseName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        builder.append(snakeCaseName).append("=").append(property);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
