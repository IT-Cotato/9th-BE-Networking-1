package com.cotato.networking1.estate.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.dto.request.EstatePostRequest;
import com.cotato.networking1.estate.dto.response.EstatePostResponse;
import com.cotato.networking1.estate.dto.response.EstatesResponse;
import com.cotato.networking1.estate.domain.Estate;
import com.cotato.networking1.estate.dto.response.EstateResponse;
import com.cotato.networking1.estate.repository.EstateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EstateService {

	private final EstateRepository estateRepository;

	private List<Estate> findEstatesByZipCode(String zipCode) {
		return estateRepository.findAllByZipCode(zipCode);
	}

	@Transactional(readOnly = true)
	public EstatesResponse makeEstateResponses(String zipCode) {
		List<EstateResponse> responses = findEstatesByZipCode(zipCode).stream()
			.map(EstateResponse::create)
			.toList();

		return EstatesResponse.create(responses);
	}

	public EstatePostResponse saveEstate(String zipCode, String roadNameAddress, String landLotNameAddress) {
		Estate estate = Estate.create(zipCode, roadNameAddress, landLotNameAddress);

		estate = estateRepository.saveAndFlush(estate);

		return EstatePostResponse.create(estate.getId());
	}

	public DataResponse deleteEstate(String roadNameAddress) {
		List<Estate> estates = estateRepository.findAllByRoadNameAddress(roadNameAddress);

		if (estates.isEmpty()) {
			return DataResponse.error(HttpStatus.NOT_FOUND.value());
		}

		estates.forEach(estate -> estateRepository.deleteById(estate.getId()));
		return DataResponse.ok();
	}

}