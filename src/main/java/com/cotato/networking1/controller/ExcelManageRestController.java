package com.cotato.networking1.controller;

import com.cotato.networking1.service.ExcelManageService;
import com.cotato.networking1.service.ExcelManageServiceImpl;
import java.io.IOException;
import java.text.SimpleDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExcelManageRestController {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss.SSS");

  private final ExcelManageService excelManageService;

  @Autowired
  public ExcelManageRestController(ExcelManageServiceImpl excelManageService) {
    this.excelManageService = excelManageService;
  }

  @GetMapping(path = "/api/jpa-upload")
  public ResponseEntity<String> jpaUpload() throws IOException {
    long start = System.currentTimeMillis();
    excelManageService.jpaUpload();
    log.info("JPA Insert Execution Time :" + simpleDateFormat.format(System.currentTimeMillis() - start));
    return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
  }

  @GetMapping(path = "/api/bulk-upload")
  public ResponseEntity<String> bulkUpload() throws IOException {
    long start = System.currentTimeMillis();
    excelManageService.bulkUpload();
    log.info("Bulk Insert Execution Time :" + simpleDateFormat.format(System.currentTimeMillis() - start));
    return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
  }

//  @DeleteMapping(path = "/api/jpa-delete")
//  public ResponseEntity<String> jpaDeleteAll() throws IOException {
//    long start = System.currentTimeMillis();
//    excelManageService.jpaDeleteAll();
//    log.info("JPA Delete Execution Time :" + simpleDateFormat.format(System.currentTimeMillis() - start));
//    return new ResponseEntity<>("JPA Delete Completed!!", HttpStatus.NO_CONTENT);
//  }

//  @DeleteMapping(path = "/api/bulk-delete")
//  public ResponseEntity<String> bulkDeleteAll() throws IOException {
//    long start = System.currentTimeMillis();
//    excelManageService.bulkDeleteAll();
//    log.info("Bulk Delete Execution Time :" + (System.currentTimeMillis() - start));
//    return new ResponseEntity<>("Bulk Delete Completed!!", HttpStatus.NO_CONTENT);
//  }

}