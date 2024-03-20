package com.cotato.networking1.estate.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.dto.request.EstatePostRequest;
import com.cotato.networking1.estate.dto.response.EstatePostResponse;
import com.cotato.networking1.estate.dto.response.EstatesResponse;
import com.cotato.networking1.estate.service.EstateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class EstateController {

	private final EstateService estateService;

	@GetMapping
	public EstatesResponse get(@RequestParam(value = "zip-code") String zipCode) {
		return estateService.makeEstateResponses(Integer.getInteger(zipCode));
	}

	@PostMapping
	public EstatePostResponse post(@RequestBody EstatePostRequest estatePostRequest) {
		return estateService.saveEstate(estatePostRequest);
	}

	@DeleteMapping
	public DataResponse delete(@RequestParam(value = "roadNameAddress") String roadNameAddress) {
		return estateService.deleteEstate(roadNameAddress);
	}
}
