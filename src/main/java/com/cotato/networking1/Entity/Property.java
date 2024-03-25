package com.cotato.networking1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postalCode;

    private String roadNameAddress;

    private String landLotNumAddress;

    @Builder
    private Property(Long id, String postalCode, String roadNameAddress, String landLotNumAddress){
        this.id = id;
        this.postalCode = postalCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNumAddress = landLotNumAddress;
    }
}
