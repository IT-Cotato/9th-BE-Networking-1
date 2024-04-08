package com.cotato.networking1.domain.dto;

import lombok.*;

public record RealEstateRegisterRequest(
        String zipCode,
        String roadNameAddress,
        String locationAddress) {
}
