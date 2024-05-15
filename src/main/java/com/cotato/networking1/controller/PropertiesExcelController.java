package com.cotato.networking1.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cotato.networking1.service.PropertiesExcelService;

@RestController
@RequestMapping(path = "/api/test-data")
public class PropertiesExcelController {

    private final PropertiesExcelService propertiesExcelService;

    @Autowired
    public PropertiesExcelController(PropertiesExcelService propertiesExcelService) {
        this.propertiesExcelService = propertiesExcelService;
    }

    @PostMapping("")
    public ResponseEntity<String> insertPropertyData() {

        return ResponseEntity.ok(propertiesExcelService.insertPropertyData("C:/Users/yejin/dev/9th-BE-Networking-1/property2.xlsx"));

    }

    @PostMapping("/2")
    public ResponseEntity<String> insertPropertyData2() {

        return ResponseEntity.ok(propertiesExcelService.insertPropertyData2("C:/Users/yejin/dev/9th-BE-Networking-1/property2.xlsx"));

    }

    @PostMapping("/3")
    public ResponseEntity<String> insertPropertyData3() {

        return ResponseEntity.ok(propertiesExcelService.insertPropertyData3("C:/Users/yejin/dev/9th-BE-Networking-1/property2.xlsx"));

    }
}





