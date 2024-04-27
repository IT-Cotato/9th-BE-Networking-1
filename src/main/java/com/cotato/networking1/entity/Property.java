package com.cotato.networking1.entity;

import com.cotato.networking1.dto.PropertyPostRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipCode; // 우편번호
    private String roadNameAddress; // 도로명 주소
    private String landLotNameAddress; // 지번

    public Property(String zipCode, String roadAddress, String lotNumberAddress) {
        this.zipCode = zipCode;
        this.roadNameAddress = roadAddress;
        this.landLotNameAddress = lotNumberAddress;
    }

    public static Property toProperty(PropertyPostRequestDto propertyPostRequestDto) {
        return new Property(
                propertyPostRequestDto.getZipCode(),
                propertyPostRequestDto.getRoadNameAddress(),
                propertyPostRequestDto.getLandLotNameAddress());
    }
}
