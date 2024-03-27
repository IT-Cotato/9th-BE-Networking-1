package com.cotato.networking1.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PropertyRegisterRequest(String zipCode,
                                      String roadNameAddress,
                                      String landLotNameAddress
) {

}
