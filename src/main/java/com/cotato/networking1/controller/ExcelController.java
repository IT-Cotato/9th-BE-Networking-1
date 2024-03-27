package com.cotato.networking1.controller;

import com.cotato.networking1.service.ExcelParsingService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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

    private final ExcelParsingService excelParsingService;

    @PostMapping("/test-data")
    public ResponseEntity<Void> createTestData(@RequestParam("file") MultipartFile file) throws IOException {
        excelParsingService.parseExcelFile(file);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}