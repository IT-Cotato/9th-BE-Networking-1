package com.cotato.networking1.domain.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@Table(name = "real_estate")
@Getter@Setter
public class Property{


    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    //bulk insert 사용
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1000")
            }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SequenceGenerator"
    )
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









