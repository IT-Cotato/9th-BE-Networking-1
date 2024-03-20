package com.cotato.networking1.controller;

import com.cotato.networking1.domain.enttiy.Property;
import com.cotato.networking1.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
