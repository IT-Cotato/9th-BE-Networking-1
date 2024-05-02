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
        long beforeTime = System.currentTimeMillis();
        String result = propertyTestDataService.insertPropertyTestData("C:\\Users\\MINUK\\Desktop\\networking\\9th-BE-Networking-1\\서울시_20만개 (1).xlsx");
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime) / 1000;

        System.out.println("소요시간 : " + secDiffTime + "초");
        return ResponseEntity.ok(result);
    }
}
