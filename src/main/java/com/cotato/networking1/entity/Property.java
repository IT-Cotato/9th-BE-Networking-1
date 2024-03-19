package com.cotato.networking1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "zipcode")
  private Integer zipCode;

  @Column(name = "land_lot_name_address")
  private String landLotNameAddress;

  @Column(name = "road_name_address")
  private String roadNameAddress;

  @Builder
  public Property(Long id, Integer zipCode, String roadNameAddress, String landLotNameAddress) {
    this.id = id;
    this.zipCode = zipCode;
    this.roadNameAddress = roadNameAddress;
    this.landLotNameAddress = landLotNameAddress;
  }
}
