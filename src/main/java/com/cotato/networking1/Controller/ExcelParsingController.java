package com.cotato.networking1.Controller;


import com.cotato.networking1.Service.ExcelParsingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/test-data")
@RequiredArgsConstructor
public class ExcelParsingController {

    private final ExcelParsingService excelParsingService;

    @PostMapping
    public ResponseEntity insertTestData() throws InvalidFormatException, IOException {
        excelParsingService.upload();
        return new ResponseEntity(HttpStatus.OK);
    }
}
