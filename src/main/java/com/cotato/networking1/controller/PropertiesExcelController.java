package com.cotato.networking1.controller;

//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cotato.networking1.service.PropertiesExcelService;

@RestController
@RequestMapping(path = "/api/test-data", consumes = MediaType.APPLICATION_JSON_VALUE)
public class PropertiesExcelController {

    private final PropertiesExcelService propertiesExcelService;

    @Autowired
    public PropertiesExcelController(PropertiesExcelService propertiesExcelService) {
        this.propertiesExcelService = propertiesExcelService;
    }

    @PostMapping("")
    public ResponseEntity<String> insertPropertyData() {

        return ResponseEntity.ok(propertiesExcelService.insertPropertyData("C:/Users/yejin/dev/9th-BE-Networking-1/property.xlsx"));

    }
}





