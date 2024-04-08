package com.cotato.networking1.domain.dto;

import java.util.List;

public record RealEstateListResponse(
        List<RealEstateResponse> realEstates
) {
    public static RealEstateListResponse from(List<RealEstateResponse> realEstates){
        return new RealEstateListResponse(realEstates);
    }
}
