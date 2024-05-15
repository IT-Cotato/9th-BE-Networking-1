package com.cotato.networking1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.TableGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@TableGenerator(
//        name = "PROPERTY_SEQ_GENERATOR",
//        table = "SEQUENCE_TABLE",
//        pkColumnName = "sequence_name" ,
//        valueColumnName = "PROPERTY_SEQ",
//        initialValue = 1,
//        allocationSize = 200000
//)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
//            ,generator = "PROPERTY_SEQ_GENERATOR"
    )
    @Column(name = "property_id")
    private Long id;

    @Column(name = "property_zipcode")
    private String zipCode;

    @Column(name = "property_road_name_address")
    private String roadNameAddress;

    @Column(name = "property_land_lot_name_address")
    private String landLotNameAddress;

    private Property(String zipCode, String roadNameAddress, String landLotNameAddress) {
        this.zipCode = zipCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNameAddress = landLotNameAddress;
    }

    public static Property of(String zipCode, String roadNameAddress, String landLotNameAddress) {
        return new Property(zipCode, roadNameAddress, landLotNameAddress);
    }
}
