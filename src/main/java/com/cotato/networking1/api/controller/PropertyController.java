package com.cotato.networking1.api.controller;

import com.cotato.networking1.api.dto.FindPropertiesResponse;
import com.cotato.networking1.domain.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<FindPropertiesResponse> findProperty(@RequestParam("zip-code") String zipCode){
        return ResponseEntity.ok(propertyService.findProperties(zipCode));
    }
}
