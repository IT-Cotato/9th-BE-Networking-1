package com.cotato.networking1.estate.dto.response;

import java.util.List;

import lombok.Getter;

@Getter
public class EstatesResponse {

	private final List<EstateResponse> properties;

	private EstatesResponse(List<EstateResponse> properties) {
		this.properties = properties;
	}

	public static EstatesResponse create(List<EstateResponse> properties) {
		return new EstatesResponse(properties);
	}
}
