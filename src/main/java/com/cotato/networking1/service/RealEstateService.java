package com.cotato.networking1.service;

import com.cotato.networking1.domain.dto.RealEstateListResponse;
import com.cotato.networking1.domain.dto.RealEstateRegisterRequest;
import com.cotato.networking1.domain.dto.RealEstateRegisterResponse;
import com.cotato.networking1.domain.dto.RealEstateResponse;
import com.cotato.networking1.domain.entity.RealEstate;
import com.cotato.networking1.repository.RealEstateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    public RealEstateListResponse getAllByZipCode(String zipCode){
        List<RealEstateResponse> estates = realEstateRepository.findAllByZipCode(zipCode)
                .stream()
                .map(RealEstateResponse::from)
                .toList();
        return RealEstateListResponse.from(estates);
    }


    @Transactional
    public RealEstateRegisterResponse registerEstate(RealEstateRegisterRequest realEstateRegisterRequest){
        RealEstate realEstate = realEstateRepository.save(RealEstate.of(realEstateRegisterRequest));
        return new RealEstateRegisterResponse(realEstate.getId());

    }
    @Transactional
    public void deleteByRoadNameAddress(String roadNameAddress){
        realEstateRepository.deleteByRoadNameAddress(roadNameAddress);
    }
}
