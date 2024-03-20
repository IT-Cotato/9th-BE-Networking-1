package com.cotato.networking1.controller;

import com.cotato.networking1.service.ExcelManageServiceImpl;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExcelManageRestController {


  private final ExcelManageServiceImpl excelManageService;

  @Autowired
  public ExcelManageRestController(ExcelManageServiceImpl excelManageService) {
    this.excelManageService = excelManageService;
  }

  @GetMapping(path = "/api/test-data")
  public ResponseEntity<String> upload() throws IOException {
    excelManageService.upload();
    return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
  }

}