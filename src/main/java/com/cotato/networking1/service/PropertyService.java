package com.cotato.networking1.service;

import com.cotato.networking1.domain.dto.PropertyListResponse;
import com.cotato.networking1.domain.dto.PropertyRegisterRequest;
import com.cotato.networking1.domain.dto.PropertyRegisterResponse;
import com.cotato.networking1.domain.dto.PropertyResponse;
import com.cotato.networking1.domain.enttiy.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyListResponse getAllByZipCode(String zipCode) {
        List<PropertyResponse> properties = propertyRepository.findAllByZipCode(zipCode)
                .stream()
                .map(PropertyResponse::from)
                .toList();
        return PropertyListResponse.from(properties);
    }

    @Transactional
    public PropertyRegisterResponse registerProperty(PropertyRegisterRequest propertyRegisterRequest) {
        Property property = propertyRepository.save(Property.of(propertyRegisterRequest));
        return new PropertyRegisterResponse(property.getId());
    }

    @Transactional
    public void deleteByRoadNameAddress(String roadNameAddress) {
        propertyRepository.deleteByRoadNameAddress(roadNameAddress);
    }
}
