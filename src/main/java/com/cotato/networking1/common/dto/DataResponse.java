package com.cotato.networking1.common.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DataResponse {
	private final int statusCode;

	private DataResponse(int statusCode) {
		this.statusCode = statusCode;
	}

	public static DataResponse ok() {
		return new DataResponse(HttpStatus.OK.value());
	}
}
