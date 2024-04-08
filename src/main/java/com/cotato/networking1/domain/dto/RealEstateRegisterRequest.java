package com.cotato.networking1.domain.dto;

public record RealEstateRegisterRequest(
        String zipCode,
        String roadNameAddress,
        String locationAddress) {

    public RealEstateRegisterRequest {
        if (zipCode == null) {
            throw new IllegalArgumentException("Zip code must not be null");
        }
        if (roadNameAddress == null) {
            throw new IllegalArgumentException("Road name address must not be null");
        }
        if (locationAddress == null) {
            throw new IllegalArgumentException("Location address must not be null");
        }
    }
}
