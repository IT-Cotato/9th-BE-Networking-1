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
