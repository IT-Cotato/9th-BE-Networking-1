package com.cotato.networking1.service;

import com.cotato.networking1.dto.PropertyDto;
import java.util.List;

public interface PropertyService {

  List<PropertyDto> findPropertyByZipCode(int zipCode); // 우편번호에 해당하는 모든 매물 정보

  Long enrollProperty(PropertyDto propertyDto); // 매물등록

  void deletePropertyByRoadNameAddress(String roadNameAddress); // 도로명주소에 해당하는 매물 삭제

}
