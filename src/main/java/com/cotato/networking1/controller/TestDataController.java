package com.cotato.networking1.controller;

import com.cotato.networking1.service.PropertyService;
import com.cotato.networking1.service.PropertyTestDataService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test-data")
public class TestDataController {
    private final PropertyTestDataService propertyTestDataService;

    @PostMapping("")
    public ResponseEntity<String> insertPropertyTestData() throws IOException, InvalidFormatException {
        String result = propertyTestDataService.insertPropertyTestData("C:\\Users\\MINUK\\Desktop\\networking\\9th-BE-Networking-1\\서울시_20만개 (1).xlsx");

        return ResponseEntity.ok(result);
    }
}
