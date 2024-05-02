package com.cotato.networking1.domain;


import com.cotato.networking1.dto.RegisterNewPropertyRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id", nullable = false)
    private Long id;
    @Column(name = "post_code", nullable = false)
    private String postCode;
    @Column(name = "road_address", nullable = false)
    private String roadAddress;
    @Column(name = "local_address", nullable = false)
    private String localAddress;

    public static Property of(RegisterNewPropertyRequest registerNewPropertyRequest) {
        return Property.builder().localAddress(registerNewPropertyRequest.localAddress())
                .postCode(registerNewPropertyRequest.postCode())
                .roadAddress(registerNewPropertyRequest.roadAddress())
                .build();
    }
}