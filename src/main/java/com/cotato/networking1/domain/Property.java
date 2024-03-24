package com.cotato.networking1.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propertyId")
    private Integer id;
    private Integer postCode;
    private String roadAddress;
    private String localAddress;
}