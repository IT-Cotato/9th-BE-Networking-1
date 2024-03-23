package com.cotato.networking1.estate.domain;

import com.cotato.networking1.estate.dto.request.EstatePostRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Estate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String roadNameAddress;

	@Column(nullable = false)
	private String landLotNameAddress;

	private Estate(String zipCode, String roadNameAddress, String landLotNameAddress) {
		this.zipCode = zipCode;
		this.roadNameAddress = roadNameAddress;
		this.landLotNameAddress = landLotNameAddress;
	}

	public static Estate create(String zipCode, String roadNameAddress, String landLotNameAddress) {
		return new Estate(zipCode, roadNameAddress, landLotNameAddress);
	}


}
