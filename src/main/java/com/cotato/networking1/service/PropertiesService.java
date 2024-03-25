package com.cotato.networking1.service;


import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.domain.repository.PropertiesRepository;
import com.cotato.networking1.dto.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


}
