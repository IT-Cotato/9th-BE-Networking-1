package com.cotato.networking1.DTO.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyCreateDTO {
    private String zipCode;
    private String roadNameAddress;
    private String landLotNameAddress;

    @Builder
    public PropertyCreateDTO(String zipCode, String roadNameAddress, String landLotNameAddress) {
        this.zipCode = zipCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNameAddress = landLotNameAddress;
    }
}
