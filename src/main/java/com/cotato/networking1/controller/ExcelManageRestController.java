package com.cotato.networking1.controller;

import com.cotato.networking1.service.ExcelManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelManageRestController {


  private final ExcelManageServiceImpl excelManageService;

  @Autowired
  public ExcelManageRestController(ExcelManageServiceImpl excelManageService) {
    this.excelManageService = excelManageService;
  }

  @PostMapping(path = "/api/test-data")
  public ResponseEntity<String> upload(
      @RequestParam(value = "file", required = false) MultipartFile file) {
    excelManageService.upload(file);
    return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
  }

}