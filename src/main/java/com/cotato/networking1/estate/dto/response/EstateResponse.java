package com.cotato.networking1.estate.dto.response;

import com.cotato.networking1.estate.domain.Estate;

import lombok.Getter;

@Getter
public class EstateResponse {

	private final Long id;
	private final int zipCode;
	private final String roadNameAddress;
	private final String landLotNameAddress;

	private EstateResponse(Long id, int zipCode, String roadNameAddress, String landLotNameAddress) {
		this.id = id;
		this.zipCode = zipCode;
		this.roadNameAddress = roadNameAddress;
		this.landLotNameAddress = landLotNameAddress;
	}

	public static EstateResponse create(Estate estate) {
		return new EstateResponse(estate.getId(), estate.getZipCode(), estate.getRoadNameAddress(),
			estate.getLandLotNameAddress());
	}
}
