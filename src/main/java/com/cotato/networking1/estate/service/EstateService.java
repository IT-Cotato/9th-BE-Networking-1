package com.cotato.networking1.estate.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.domain.Estate;
import com.cotato.networking1.estate.dto.response.EstatePostResponse;
import com.cotato.networking1.estate.dto.response.EstateResponse;
import com.cotato.networking1.estate.dto.response.EstatesResponse;
import com.cotato.networking1.estate.repository.EstatePostBulkRepository;
import com.cotato.networking1.estate.repository.EstateRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class EstateService {

	private final EstateRepository estateRepository;
	private final EstatePostBulkRepository estatePostBulkRepository;

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
		log.info("saveEstate({},{},{})", zipCode, roadNameAddress, landLotNameAddress);

		Estate estate = Estate.create(zipCode, roadNameAddress, landLotNameAddress);

		estate = estateRepository.save(estate);

		return EstatePostResponse.create(estate.getId());
	}

	public void saveAllEstate(List<List<String>> dataList) {
		List<Estate> estates = dataList.stream()
			.map(data -> Estate.create(data.get(0), data.get(1), data.get(2)))
			.toList();

		estateRepository.saveAll(estates);
	}

	public void saveAllEstateByBulk(List<List<String>> dataList) {
		estatePostBulkRepository.saveAll(dataList);
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
