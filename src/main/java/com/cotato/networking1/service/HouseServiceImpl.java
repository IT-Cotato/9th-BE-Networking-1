package com.cotato.networking1.service;

import com.cotato.networking1.enity.HouseDao;
import com.cotato.networking1.model.HouseDto;
import com.cotato.networking1.repository.HouseRepository;
import com.cotato.networking1.utility.FindByUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService{

    private final FindByUtil findByUtil;
    private final HouseRepository houseRepository;

    @Override
    public HouseDto houseCreateInformation(HouseDto houseDto) {
        HouseDao saveHouseEntity = houseDto.houseEntity(houseDto);
        HouseDao save = houseRepository.save(saveHouseEntity);
        HouseDto convertHouse = houseDto.convertHouseToDto(save);
        return convertHouse;
    }

    @Override
    public void houseInformationUpdate(Long id,HouseDto houseDto) {
        HouseDao existentByHouseId = findByUtil.existentByHouseId(id);
        existentByHouseId.updateHouse(houseDto);
        HouseDao save = houseRepository.save(existentByHouseId);
    }

    @Override
    public HouseDto houseInfo(Long id) {
        HouseDao existentByHouseId = findByUtil.existentByHouseId(id);
        return HouseDto.convertHouseToDto(existentByHouseId);
    }

    @Override
    public void houseRemove(Long id) {
        HouseDao existentByHouseId = findByUtil.existentByHouseId(id);
        houseRepository.delete(existentByHouseId);
    }
}
