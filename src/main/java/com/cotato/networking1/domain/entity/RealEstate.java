package com.cotato.networking1.domain.entity;

import com.cotato.networking1.domain.dto.RealEstateRegisterRequest;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Long id;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable=false)
    private String roadNameAddress;

    @Column(nullable=false)
    private String landLotNameAddress;

    public static RealEstate of(RealEstateRegisterRequest realEstateRegisterRequest){
        return RealEstate.builder()
                .landLotNameAddress(realEstateRegisterRequest.locationAddress())
                .zipCode(realEstateRegisterRequest.zipCode())
                .roadNameAddress(realEstateRegisterRequest.roadNameAddress())
                .build();
    }

}
