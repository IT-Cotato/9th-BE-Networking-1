package com.cotato.networking1.controller;


import com.cotato.networking1.dto.FindPropertiesResponse;
import com.cotato.networking1.dto.RegisterNewPropertyRequest;
import com.cotato.networking1.dto.RegisterNewPropertyResponse;
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

    @GetMapping("")
    public ResponseEntity<FindPropertiesResponse> getPropertiesByPostCode(@RequestParam("zip-code") String postCode) {
        return ResponseEntity.ok(propertyService.findPropertiesByPostCode(postCode));
    }

    @PostMapping("")
    public ResponseEntity<RegisterNewPropertyResponse> postProperty(@RequestBody RegisterNewPropertyRequest property) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.postProperty(property));
    }
}