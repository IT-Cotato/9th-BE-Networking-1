package com.cotato.networking1.dto;

import com.cotato.networking1.domain.Property;

public record PropertyResponse(Long id, String postCode, String roadAddress, String localAddress) {
    public static PropertyResponse from(Property property) {
        return new PropertyResponse(property.getId(), property.getPostCode(), property.getRoadAddress(), property.getLocalAddress());
    }
}