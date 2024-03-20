package com.cotato.networking1.estate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.domain.Estate;
import com.cotato.networking1.estate.dto.EstateResponse;
import com.cotato.networking1.estate.repository.EstateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstateService {

	private final EstateRepository estateRepository;

	private List<Estate> findEstatesByZipCode(int zipCode) {
		return estateRepository.findAllByZipCode(zipCode);
	}

	public DataResponse<List<EstateResponse>> makeEstateResponses(int zipCode) {
		List<EstateResponse> responses = findEstatesByZipCode(zipCode).stream()
			.map(EstateResponse::create)
			.toList();

		return DataResponse.ok(responses);
	}


}
