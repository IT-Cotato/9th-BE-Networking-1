package com.cotato.networking1.service;

import com.cotato.networking1.enity.HouseDao;
import com.cotato.networking1.model.HouseDto;

public interface HouseService  {

    public HouseDto houseCreateInformation(HouseDto houseDto);

    public void houseInformationUpdate(Long id,HouseDto houseDto);

    public HouseDto houseInfo(Long id);

    public void houseRemove(Long id);
}
