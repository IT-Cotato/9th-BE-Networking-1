package com.cotato.networking1.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@TableGenerator(
        name = "PROPERTY_SEQ_GENERATOR",
        table = "PROPERTY_SEQUENCE",
        pkColumnValue = "PROPERTY_SEQ",
        allocationSize = 1
)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Property {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROPERTY_SEQ_GENERATOR")
    private Long id;

    private String zipCode;

    private String roadNameAddress;

    private String landLotNameAddress;

    @Builder
    private Property(Long id, String zipCode, String roadNameAddress, String landLotNameAddress){
        this.id = id;
        this.zipCode = zipCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNameAddress = landLotNameAddress;
    }
}
