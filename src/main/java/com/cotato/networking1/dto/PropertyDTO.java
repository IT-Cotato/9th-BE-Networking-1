package com.cotato.networking1.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyDTO {
    private Long id;
    private String zipCode;
    private String roadNameAddress;
    private String landLotNameAddress;

    public PropertyDTO(Long id, String zipCode, String roadNameAddress, String landLotNameAddress) {
        this.id = id;
        this.zipCode = zipCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNameAddress = landLotNameAddress;
    }

}
