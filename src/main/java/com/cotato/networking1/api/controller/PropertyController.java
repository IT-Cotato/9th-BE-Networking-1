package com.cotato.networking1.api.controller;

import com.cotato.networking1.api.dto.CreatePropertyRequest;
import com.cotato.networking1.api.dto.CreatePropertyResponse;
import com.cotato.networking1.api.dto.FindPropertiesResponse;
import com.cotato.networking1.domain.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/properties")
    public ResponseEntity<CreatePropertyResponse> createProperty(@RequestBody @Valid CreatePropertyRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperty(request));
    }
}
