package com.cotato.networking1.service;

import com.cotato.networking1.dto.FindPropertiesResponse;
import com.cotato.networking1.dto.PropertyResponse;
import com.cotato.networking1.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public FindPropertiesResponse findPropertiesByPostCode(String postCode) {
        List<PropertyResponse> properties = propertyRepository.findPropertiesByPostCode(postCode)
                .stream().map(PropertyResponse::from).toList();
        return FindPropertiesResponse.from(properties);
    }
}
