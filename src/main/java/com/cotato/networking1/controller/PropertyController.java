package com.cotato.networking1.controller;
import com.cotato.networking1.dto.PropertyListResponse;
import com.cotato.networking1.dto.PropertyPostRequest;
import com.cotato.networking1.dto.PropertyPostResponse;
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
    public ResponseEntity<PropertyListResponse> getPropertiesByZipCode(@RequestParam("zip-code") String zipCode) {
        return ResponseEntity.ok(propertyService.findPropertiesByZipCode(zipCode));
    }

    @PostMapping()
    public ResponseEntity<PropertyPostResponse> createProperty(@RequestBody PropertyPostRequest propertyPostRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.saveProperty(propertyPostRequest));
    }

    @DeleteMapping()
    public ResponseEntity<?> deletePropertyByRoadNameAddress(@RequestParam("road-name-address") String roadNameAddress) {
        try {
            return ResponseEntity.ok(propertyService.deletePropertiesByRoadAddress(roadNameAddress));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete the properties: " + e.getMessage());
        }
    }
}
