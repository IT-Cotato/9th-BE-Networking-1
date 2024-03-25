package com.cotato.networking1.Service;

import com.cotato.networking1.DTO.Response.PropertyResponseDTO;
import com.cotato.networking1.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public List<PropertyResponseDTO> getList(String zipCode){
        return propertyRepository.findByZipCode(zipCode).stream()
                .map(PropertyResponseDTO::new)
                .collect(Collectors.toList());
    }
}
