package com.cotato.networking1.estate.dto.response;

import lombok.Getter;

@Getter
public class EstatePostResponse {

	private final Long id;

	private EstatePostResponse(Long id) {
		this.id = id;
	}

	public static EstatePostResponse create(Long id) {
		return new EstatePostResponse(id);
	}
}
