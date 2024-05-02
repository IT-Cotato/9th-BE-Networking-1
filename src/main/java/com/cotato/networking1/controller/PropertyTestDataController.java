package com.cotato.networking1.controller;

import com.cotato.networking1.service.PropertyTestDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/api/test-data")
// 엑셀 파일을 파싱 후 property 테이블에 저장하므로 RequestMapping 사용
public class PropertyTestDataController {
    private @Autowired PropertyTestDataService propertyTestDataService;

    @PostMapping("")
    public ResponseEntity<String> insertPropertyTestData() throws IOException, InvalidFormatException {
        return ResponseEntity.ok(propertyTestDataService.insertPropertyTestData("C:\\Users\\jooye\\cotato\\9th-BE-Networking-1\\src\\main\\resources\\propertyFile.xlsx"));
    }
}
