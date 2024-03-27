package com.cotato.networking1.service;

import com.cotato.networking1.dto.PropertyDto;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertiesService {

    private final PropertiesRepository propertiesRepository;

    @Transactional
    public Long save(PropertyDto propertyDto) {
        Property property = Property.of(propertyDto.getZipCode(), propertyDto.getRoadNameAddress(), propertyDto.getLandLotNameAddress());
        return propertiesRepository.save(property).getId();
    }

    @Transactional(readOnly = true)
    public List<PropertyDto> findByZipCode(String zipCode) {
        return propertiesRepository.findByZipCode(zipCode).stream()
                .map(property -> {
                    PropertyDto dto = new PropertyDto();
                    dto.setZipCode(property.getZipCode());
                    dto.setRoadNameAddress(property.getRoadNameAddress());
                    dto.setLandLotNameAddress(property.getLandLotNameAddress());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteByRoadNameAddress(String roadNameAddress) {
        Property property = propertiesRepository.findByRoadNameAddress(roadNameAddress);
        propertiesRepository.delete(property);
    }
}