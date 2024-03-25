package com.cotato.networking1.DTO.Response;

import com.cotato.networking1.Entity.Property;
import lombok.Getter;

@Getter
public class PropertyResponseDTO {
    private final Long id;
    private final String zipCode;
    private final String roadNameAddress;
    private final String landLotNameAddress;

    public PropertyResponseDTO(Property property) {
        this.id = property.getId();
        this.zipCode = property.getZipCode();
        this.roadNameAddress = property.getRoadNameAddress();
        this.landLotNameAddress = property.getLandLotNameAddress();
    }
}
