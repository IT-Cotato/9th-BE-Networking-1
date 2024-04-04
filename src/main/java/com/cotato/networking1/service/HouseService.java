package com.cotato.networking1.service;

import com.cotato.networking1.enity.HouseDao;
import com.cotato.networking1.model.HouseDto;

public interface HouseService  {

    public HouseDto houseCreateInformation(HouseDto houseDto);

    public void houseInformationUpdate(HouseDto houseDto);

    public HouseDto houseInfo(HouseDto houseDto);

    public void houseRemove(HouseDto houseDto);
}
