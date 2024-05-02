package com.cotato.networking1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "property")
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "zip-code")
  private String zipCode;

  @Column(name = "land-lot-name-address")
  private String landLotNameAddress;

  @Column(name = "road-name-address")
  private String roadNameAddress;


  @Builder
  public Property(Long id, String zipCode, String roadNameAddress, String landLotNameAddress) {
    this.id = id;
    this.zipCode = zipCode;
    this.roadNameAddress = roadNameAddress;
    this.landLotNameAddress = landLotNameAddress;
  }
}
