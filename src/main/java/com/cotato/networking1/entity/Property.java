package com.cotato.networking1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TableGenerator(
        name = "PROPERTY_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "PROPERTY_SEQ",
        initialValue = 1,
        allocationSize = 10000

)
public class Property {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PROPERTY_SEQ_GENERATOR"
    )
    private Long id;

    private String zipCode;

    private String roadNameAddress;

    private String landLotNameAddress;

    @Builder
    private Property(String zipCode, String roadNameAddress, String landLotNameAddress){
        this.zipCode = zipCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNameAddress = landLotNameAddress;
    }

}
