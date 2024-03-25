package com.cotato.networking1.api.controller;

import com.cotato.networking1.domain.service.ExcelParsingService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelParsingService excelParsingService;

    @PostMapping("/test-data")
    public ResponseEntity<Void> createTestData() throws IOException, InvalidFormatException {
        excelParsingService.createData();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
