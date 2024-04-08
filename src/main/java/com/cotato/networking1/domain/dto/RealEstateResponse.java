package com.cotato.networking1.domain.dto;

import com.cotato.networking1.domain.entity.RealEstate;
import lombok.*;


public record RealEstateResponse(
        Long id,
        String zipCode,
        String roadNameAddress,
        String landLotNameAddress
) {
    public static RealEstateResponse from (RealEstate realEstate){
        return new RealEstateResponse(
                realEstate.getId(),
                realEstate.getZipCode(),
                realEstate.getRoadNameAddress(),
                realEstate.getLandLotNameAddress()
        );
    }


}
