package com.cotato.networking1.domain.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "real_estate")
@Getter@Setter
public class Property{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "roadNameAddress")
    private String roadNameAddress;

    @Column(name = "landLotNameAddress")
    private String landLotNameAddress;

    @Builder
    public Property(Long id, String zipCode, String roadNameAddress, String landLotNameAddress){
        this.id=id;
        this.zipCode=zipCode;
        this.roadNameAddress= roadNameAddress;
        this.landLotNameAddress=landLotNameAddress;
    }



}









