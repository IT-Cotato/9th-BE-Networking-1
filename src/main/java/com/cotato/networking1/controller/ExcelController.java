package com.cotato.networking1.controller;

import com.cotato.networking1.service.ExcelService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/test-data")
    public ResponseEntity<Void> parsingPropertyData(@RequestPart(value = "file") MultipartFile multipartFile) throws IOException, InvalidFormatException {
        excelService.parsingTestData(multipartFile);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
