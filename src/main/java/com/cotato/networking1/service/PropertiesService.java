package com.cotato.networking1.service;


import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.domain.repository.PropertiesRepository;
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



}
