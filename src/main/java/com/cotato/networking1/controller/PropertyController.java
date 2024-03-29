package com.cotato.networking1.controller;

import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping()
    public ResponseEntity<List<Property>> getAllByZipCode(@RequestParam("zip-code") String zipCode) {
        return ResponseEntity.ok(propertyService.getAllByZipCode(zipCode));
    }
}
