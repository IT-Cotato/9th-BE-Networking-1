package com.cotato.networking1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropertyPostRequest {
    String zipCode;
    String roadNameAddress;
    String landLotNameAddress;
}
