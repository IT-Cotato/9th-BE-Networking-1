package com.cotato.networking1.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long id;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "land_lot_name_adress")
    private String landLotNameAddress;
}