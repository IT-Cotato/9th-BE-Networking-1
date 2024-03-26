package com.cotato.networking1.service;

import com.cotato.networking1.dto.request.PropertyRegisterRequest;
import com.cotato.networking1.dto.response.CreatePropertyResponse;
import com.cotato.networking1.dto.response.FindPropertyResponse;
import com.cotato.networking1.dto.response.PropertyResponse;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    public FindPropertyResponse findProperties(String zipCode) {
        List<PropertyResponse> list = propertyRepository.findAllByZipCode(zipCode).stream()
                .map(PropertyResponse::from)
                .toList();
        return FindPropertyResponse.from(list);
    }

    @Transactional
    public CreatePropertyResponse registerProperty(PropertyRegisterRequest request) {

        Property property = Property.builder()
                .zipCode(request.zipCode())
                .roadNameAddress(request.roadNameAddress())
                .landLotNameAddress(request.landLotNameAddress())
                .build();

        propertyRepository.save(property);
        return new CreatePropertyResponse(property.getId());
    }

    @Transactional
    public void deleteProperty(String roadNameAddress) {
        propertyRepository.deleteByRoadNameAddress(roadNameAddress);
    }
}
