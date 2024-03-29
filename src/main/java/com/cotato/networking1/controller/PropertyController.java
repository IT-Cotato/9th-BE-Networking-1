package com.cotato.networking1.controller;

import com.cotato.networking1.domain.dto.PropertyListResponse;
import com.cotato.networking1.domain.dto.PropertyRegisterResponse;
import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping()
    public ResponseEntity<PropertyListResponse> getAllByZipCode(@RequestParam("zip-code") String zipCode) {
        return ResponseEntity.status(HttpStatus.OK).body(new PropertyListResponse(propertyService.getAllByZipCode(zipCode)));
    }

    @PostMapping()
    public ResponseEntity<PropertyRegisterResponse> registerProperty(@RequestBody Property property) {
        return ResponseEntity.status(HttpStatus.OK).body(new PropertyRegisterResponse(propertyService.registerProperty(property)));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteProperty(@RequestParam("road-name-address") String roadNameAddress) {
        propertyService.deleteByRoadNameAddress(roadNameAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
