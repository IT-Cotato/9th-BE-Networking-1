package com.cotato.networking1.domain.service;

import com.cotato.networking1.api.dto.CreatePropertyRequest;
import com.cotato.networking1.api.dto.CreatePropertyResponse;
import com.cotato.networking1.api.dto.FindPropertiesResponse;
import com.cotato.networking1.api.dto.PropertyResponse;
import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.domain.repository.PropertyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public FindPropertiesResponse findProperties(String zipCode) {
        List<PropertyResponse> responses = propertyRepository.findAllByZipCode(zipCode).stream()
                .map(PropertyResponse::from)
                .toList();
        return FindPropertiesResponse.from(responses);
    }

    @Transactional
    public CreatePropertyResponse createProperty(CreatePropertyRequest request) {
        Property createdProperty = Property.of(request.zipCode(), request.roadNameAddress(),
                request.landLotNameAddress());
        propertyRepository.save(createdProperty);
        return new CreatePropertyResponse(createdProperty.getId());
    }
}
