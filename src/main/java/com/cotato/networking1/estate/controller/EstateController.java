package com.cotato.networking1.estate.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.dto.EstateResponse;
import com.cotato.networking1.estate.service.EstateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class EstateController {

	private final EstateService estateService;

	@GetMapping
	public DataResponse<List<EstateResponse>> getEstates(@RequestParam(value = "zip-code") String zipCode) {
		return estateService.makeEstateResponses(Integer.getInteger(zipCode));
	}

}
