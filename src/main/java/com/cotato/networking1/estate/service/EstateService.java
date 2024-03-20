package com.cotato.networking1.estate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cotato.networking1.estate.dto.response.EstatesResponse;
import com.cotato.networking1.estate.domain.Estate;
import com.cotato.networking1.estate.dto.response.EstateResponse;
import com.cotato.networking1.estate.repository.EstateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstateService {

	private final EstateRepository estateRepository;

	private List<Estate> findEstatesByZipCode(int zipCode) {
		return estateRepository.findAllByZipCode(zipCode);
	}

	public EstatesResponse makeEstateResponses(int zipCode) {
		List<EstateResponse> responses = findEstatesByZipCode(zipCode).stream()
			.map(EstateResponse::create)
			.toList();

		return EstatesResponse.create(responses);
	}


}
