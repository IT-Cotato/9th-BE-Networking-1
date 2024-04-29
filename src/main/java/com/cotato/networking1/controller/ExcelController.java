package com.cotato.networking1.controller;

import com.cotato.networking1.service.PropertyTestDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExcelController {

    private final PropertyTestDataService propertyTestDataService;

    @PostMapping("/test-data")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            propertyTestDataService.saveProperties(file);
            return ResponseEntity.ok("File uploaded and data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
        }
    }
}
