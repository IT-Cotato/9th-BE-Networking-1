package com.cotato.networking1.domain;


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
    private Integer id;
    @Column(name = "post_code", nullable = false)
    private String postCode;
    @Column(name = "road_address", nullable = false)
    private String roadAddress;
    @Column(name = "local_address", nullable = false)
    private String localAddress;
}