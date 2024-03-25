package com.cotato.networking1.controller;


import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.dto.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cotato.networking1.service.PropertiesService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PropertiesController {

    private final PropertiesService propertiesService;

    @Autowired
    public PropertiesController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @GetMapping("/api/properties")
    public ResponseEntity<Object> getPropertiesByZipCode(@RequestParam("zip-code") String zipCode) {
        List<Property> properties = propertiesService.getPropertiesByZipCode(zipCode);
        List<PropertyDTO> propertyDTOs = properties.stream()
                .map(property -> new PropertyDTO(
                        property.getId(),
                        property.getZipCode(),
                        property.getRoadNameAddress(),
                        property.getLandLotNameAddress()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(Map.of("properties", propertyDTOs));
    }

    @PostMapping("/api/properties")
    public ResponseEntity<Object> addProperty(@RequestBody PropertyDTO propertyDTO) {
        Long propertyId = propertiesService.addProperty(propertyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", propertyId));
    }


}