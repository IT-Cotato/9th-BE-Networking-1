package com.cotato.networking1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDtoReq {
    private String zipCode;
    private String roadAddress;
    private String lotNumberAddress;
}
