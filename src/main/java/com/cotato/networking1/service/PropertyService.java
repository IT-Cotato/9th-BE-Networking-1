package com.cotato.networking1.service;

import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public List<Property> getAllByZipCode(String zipCode) {
        return propertyRepository.findAllByZipCode(zipCode);
    }
}
