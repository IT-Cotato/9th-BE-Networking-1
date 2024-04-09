package com.cotato.networking1.model;

import com.cotato.networking1.enity.HouseDao;
import lombok.Getter;

@Getter
public class HouseDto {

    private String zipCode;

    private String roadNameAddress;

    private String landLotNameAddress;


    public HouseDto(String zipCode, String roadNameAddress, String landLotNameAddress) {
        this.zipCode = zipCode;
        this.roadNameAddress =roadNameAddress;
        this.landLotNameAddress = roadNameAddress;

    }

    public HouseDao houseEntity(HouseDto houseDto){
        return HouseDao.builder()
                .zipCode(houseDto.getZipCode())
                .roadNameAddress(houseDto.roadNameAddress)
                .landLotNameAddress(houseDto.getRoadNameAddress())
                .build();
    }

    public static HouseDto convertHouseToDto(HouseDao houseDao){
        return new HouseDto(houseDao.getZipCode(), houseDao.getRoadNameAddress(), houseDao.getLandLotNameAddress());
    }
}
