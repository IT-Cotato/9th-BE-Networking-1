package com.cotato.networking1.service;

import com.cotato.networking1.dto.PropertyDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface PropertyService {

  //
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file);
  public List<PropertyDto> findPropertyByZipCode(int zipCode); // 우편번호에 해당하는 모든 매물 정보
  public Long enrollProperty(PropertyDto propertyDto); // 매물등록
  public void deletePropertyByRoadNameAddress(String roadNameAddress); // 도로명주소에 해당하는 매물 삭제

}
