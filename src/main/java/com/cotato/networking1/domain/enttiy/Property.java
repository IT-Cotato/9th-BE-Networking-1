package com.cotato.networking1.domain.enttiy;

import com.cotato.networking1.domain.dto.PropertyRegisterRequest;
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
    private String zipCode;
    private String roadNameAddress;
    private String landLotNameAddress;

    public static Property of(PropertyRegisterRequest propertyRegisterRequest) {
        return Property.builder()
                .landLotNameAddress(propertyRegisterRequest.landLotNameAddress())
                .zipCode(propertyRegisterRequest.zipCode())
                .roadNameAddress(propertyRegisterRequest.roadNameAddress())
                .build();
    }
}
