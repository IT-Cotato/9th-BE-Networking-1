package com.cotato.networking1.api.dto;

import jakarta.validation.constraints.NotNull;

public record CreatePropertyRequest(
        @NotNull
        String zipCode,
        @NotNull
        String roadNameAddress,
        @NotNull
        String landLotNameAddress
) {
}
