package com.cotato.networking1.service;


import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.domain.repository.PropertiesRepository;
import com.cotato.networking1.dto.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropertiesService {

    private final PropertiesRepository propertiesRepository;

    @Autowired
    public PropertiesService(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    public List<Property> getPropertiesByZipCode(String zipCode) {
        return propertiesRepository.findByZipCode(zipCode);
    }

    public Long addProperty(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setZipCode(propertyDTO.getZipCode());
        property.setRoadNameAddress(propertyDTO.getRoadNameAddress());
        property.setLandLotNameAddress(propertyDTO.getLandLotNameAddress());

        Property savedProperty = propertiesRepository.save(property);
        return savedProperty.getId();
    }

    @Transactional
    public ResponseEntity<String> deletePropertyByRoadNameAddress(String roadNameAddress) {
        // 해당 도로명 주소를 가진 매물을 데이터베이스에서 조회합니다.
        Property property = propertiesRepository.findByRoadNameAddress(roadNameAddress);

        // 조회된 매물이 있으면 삭제를 수행합니다.
        if (property != null) {
            propertiesRepository.delete(property);
            return ResponseEntity.ok("Property with road name address '" + roadNameAddress + "' deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property with road name address '" + roadNameAddress + "' not found");
        }
    }


}
