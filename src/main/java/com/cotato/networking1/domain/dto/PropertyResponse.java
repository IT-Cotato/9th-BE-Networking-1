package com.cotato.networking1.domain.dto;

import com.cotato.networking1.domain.enttiy.Property;

public record PropertyResponse(
        Long id,
        String zipCode,
        String roadNameAddress,
        String landLotNameAddress
) {
    public static PropertyResponse from(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getZipCode(),
                property.getRoadNameAddress(),
                property.getLandLotNameAddress()
        );
    }
}