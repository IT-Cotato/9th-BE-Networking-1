package com.cotato.networking1.dto;

import java.util.List;

public record PropertyListResponse(
        List<PropertyResponse> properties
) {
    public static PropertyListResponse from(List<PropertyResponse> properties) {
        return new PropertyListResponse(properties);
    }
}
