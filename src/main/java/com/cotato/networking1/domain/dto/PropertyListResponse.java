package com.cotato.networking1.domain.dto;

import com.cotato.networking1.domain.enttiy.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public record PropertyListResponse(
        List<PropertyResponse> properties
) {
    public static PropertyListResponse from(List<PropertyResponse> properties) {
        return new PropertyListResponse(properties);
    }
}
