package com.cotato.networking1.controller;


import com.cotato.networking1.dto.FindPropertiesResponse;
import com.cotato.networking1.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("")
    public ResponseEntity<FindPropertiesResponse> getPropertiesByPostCode(@RequestParam("post-code") String postCode) {
        return ResponseEntity.ok(propertyService.findPropertiesByPostCode(postCode));
    }
}