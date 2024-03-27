package com.cotato.networking1.controller;

import com.cotato.networking1.dto.PropertyDto;
import com.cotato.networking1.service.PropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertiesService propertiesService;

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getPropertiesByZipCode(@RequestParam("zip-code") String zipCode) {
        List<PropertyDto> properties = propertiesService.findByZipCode(zipCode);
        return ResponseEntity.ok(properties);
    }

    @PostMapping("/properties")
    public ResponseEntity<Long> createProperty(@RequestBody PropertyDto propertyDto) {
        Long id = propertiesService.save(propertyDto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/properties")
    public ResponseEntity<Void> deleteProperty(@RequestParam("road-name-address") String roadNameAddress) {
        propertiesService.deleteByRoadNameAddress(roadNameAddress);
        return ResponseEntity.noContent().build();
    }
}