package com.cotato.networking1.service;

import com.cotato.networking1.dto.*;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Transactional
    public PropertyPostResponse saveProperty(PropertyPostRequest propertyPostRequest) {
        Property property = propertyRepository.save(Property.toProperty(propertyPostRequest));
        return new PropertyPostResponse(property.getId());
    }

    public PropertyListResponse findPropertiesByZipCode(String zipCode) {
        List<PropertyResponse> propertyResponses = propertyRepository.findAllByZipCode(zipCode)
                .stream()
                .map(PropertyResponse::from)
                .toList();
        return new PropertyListResponse(propertyResponses);
    }

    @Transactional
    public PropertyDeleteListResponse deletePropertiesByRoadAddress(String roadAddress) {
        List<Long> propertyDeleteIds = propertyRepository.findAllByRoadNameAddress(roadAddress)
                .stream()
                .map(Property::getId)
                .collect(Collectors.toList());

        propertyRepository.deleteAllByIds(propertyDeleteIds);
        return PropertyDeleteListResponse.from(propertyDeleteIds);
    }
}
