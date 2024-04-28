package com.cotato.networking1.service;

import com.cotato.networking1.dto.PropertyListResponse;
import com.cotato.networking1.dto.PropertyResponse;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public PropertyListResponse findPropertiesByZipCode(String zipCode) {
        List<PropertyResponse> propertyResponses = propertyRepository.findAllByZipCode(zipCode)
                .stream()
                .map(PropertyResponse::from)
                .toList();
        return new PropertyListResponse(propertyResponses);
    }

    public List<Long> deletePropertiesByRoadAddress(String roadAddress) {
        List<Property> properties = propertyRepository.findByRoadNameAddress(roadAddress);
        List<Long> deletedIds = properties.stream()
                .map(Property::getId)
                .collect(Collectors.toList()); // 삭제될 매물의 ID 수집
        propertyRepository.deleteAll(properties); // 매물 삭제
        return deletedIds; // 삭제된 매물의 ID 반환
    }


}
