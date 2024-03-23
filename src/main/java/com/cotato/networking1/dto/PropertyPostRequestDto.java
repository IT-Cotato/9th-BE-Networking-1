package com.cotato.networking1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
@AllArgsConstructor
public class PropertyPostRequestDto {

    Long id;
    String zipCode;
    String roadNameAddress;
    String landLotNameAddress;
}
