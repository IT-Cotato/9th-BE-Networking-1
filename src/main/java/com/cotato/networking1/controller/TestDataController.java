package com.cotato.networking1.controller;

import com.cotato.networking1.service.RealEstateTestDataService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test-data")
public class TestDataController {
    private final RealEstateTestDataService realEstateTestDataService;

    @PostMapping("")
    public ResponseEntity<String> insertRealEstateTestData() throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        return ResponseEntity.ok(realEstateTestDataService.insertRealEstateTestData("C:/Users/user/OneDrive/바탕 화면/코테이토/9기/excel.xlsx"));
    }
}
