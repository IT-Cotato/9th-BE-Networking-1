package com.cotato.networking1.controller;

import com.cotato.networking1.dto.request.PropertyRegisterRequest;
import com.cotato.networking1.dto.response.CreatePropertyResponse;
import com.cotato.networking1.dto.response.FindPropertyResponse;
import com.cotato.networking1.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<FindPropertyResponse> findPropertiesByZipCode(@RequestParam(name = "zip-code") String zipCode){
        return ResponseEntity.status(HttpStatus.OK).body(propertyService.findProperties(zipCode));
    }

    @PostMapping("/properties")
    public ResponseEntity<CreatePropertyResponse> registerProperty(@RequestBody PropertyRegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.registerProperty(request));
    }

    @DeleteMapping("/properties")
    public ResponseEntity<?> deleteProperty(@RequestParam("road-name-address") String roadNameAddress){
        propertyService.deleteProperty(roadNameAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
