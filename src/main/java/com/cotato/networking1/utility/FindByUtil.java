package com.cotato.networking1.utility;

import com.cotato.networking1.enity.HouseDao;
import com.cotato.networking1.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindByUtil {

    private final HouseRepository houseRepository;

    public HouseDao existentByHouseId(Long id){
        HouseDao houseFindById = houseRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("없는 매물 입니다"));
        return houseFindById;
    }

}
