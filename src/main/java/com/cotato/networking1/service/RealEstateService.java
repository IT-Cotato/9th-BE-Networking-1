package com.cotato.networking1.service;

import com.cotato.networking1.domain.dto.RealEstateDTO;
import com.cotato.networking1.domain.dto.RealEstateListDTO;
import com.cotato.networking1.domain.dto.RequestDTO;
import com.cotato.networking1.domain.dto.ResponseDTO;
import com.cotato.networking1.domain.entity.RealEstate;
import com.cotato.networking1.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;
    public RealEstateListDTO getAllByZipCode(String zipCode){
        return null;
    }
    public ResponseDTO registerEstate(RequestDTO request){
        return null;
    }
    public void deleteEstate(String roadNameAddress){

    }



}
