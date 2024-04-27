package com.cotato.networking1.dto;

import com.cotato.networking1.entity.Property;

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
