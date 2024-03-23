package com.cotato.networking1.controller;

import com.cotato.networking1.dto.PropertyPostRequestDto;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/test-data")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            propertyService.saveProperties(file);
            return ResponseEntity.ok("File uploaded and data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
        }
    }

    @GetMapping("/properties")
    public ResponseEntity<?> getPropertiesByZipCode(@RequestParam("zip-code") String zipCode) {
        List<Property> properties = propertyService.findPropertiesByZipCode(zipCode);
        List<Map<String, Object>> response = properties.stream().map(property -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", property.getId());
            map.put("zipCode", property.getZipCode());
            map.put("roadNameAddress", property.getRoadAddress());
            map.put("landLotNameAddress", property.getLotNumberAddress());
            return map;
        }).collect(Collectors.toList());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("properties", response);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/properties")
    public ResponseEntity<?> createProperty(@RequestBody PropertyPostRequestDto propertyPostRequestDto) {
        Property property = Property.toProperty(propertyPostRequestDto);
        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok().body(Map.of("id", savedProperty.getId()));
    }

    @DeleteMapping("/properties")
    public ResponseEntity<?> deletePropertyByRoadNameAddress(@RequestParam("road-name-address") String roadAddress) {
        try {
            List<Long> deletedIds = propertyService.deletePropertiesByRoadAddress(roadAddress);
            Map<String, Object> response = new HashMap<>();
            response.put("message", deletedIds.size() + " properties with road address '" + roadAddress + "' have been deleted successfully.");
            response.put("deletedIds", deletedIds);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete the properties: " + e.getMessage());
        }
    }

}
