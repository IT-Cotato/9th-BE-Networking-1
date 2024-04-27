package com.cotato.networking1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PropertyPostRequestDto {
    String zipCode;
    String roadNameAddress;
    String landLotNameAddress;
}
