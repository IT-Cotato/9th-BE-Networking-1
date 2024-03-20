package com.cotato.networking1.estate.domain;

import com.cotato.networking1.estate.dto.EstateGetRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Estate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int zipCode;

	@Column(nullable = false)
	private String roadNameAddress;

	@Column(nullable = false)
	private String landLotNameAddress;

	private Estate(int zipCode, String roadNameAddress, String landLotNameAddress) {
		this.zipCode = zipCode;
		this.roadNameAddress = roadNameAddress;
		this.landLotNameAddress = landLotNameAddress;
	}

	public Estate create(EstateGetRequest estateGetRequest) {
		return new Estate(
			estateGetRequest.getZipCode(),
			estateGetRequest.getRoadNameAddress(),
			estateGetRequest.getLandLotNameAddress());
	}


}
