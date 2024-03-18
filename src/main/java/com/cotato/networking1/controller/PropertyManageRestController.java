package com.cotato.networking1.controller;

import com.cotato.networking1.dto.PropertyDto;
import com.cotato.networking1.service.PropertyManageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api",
    consumes = {MediaType.APPLICATION_JSON_VALUE})
public class PropertyManageRestController {

  private final PropertyManageServiceImpl propertyService;

  @Autowired
  public PropertyManageRestController(PropertyManageServiceImpl propertyService) {
    this.propertyService = propertyService;
  }

  @PostMapping(path = "/test-data")
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
    propertyService.upload(file);
    return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
  }

  //우편번호에 해당하는 매물 정보를 응답
  @GetMapping(path = "/properties")
  public ResponseEntity<List<PropertyDto>> getPropertiesByZipCode(
      @RequestParam("zip-code") int zipCode) {
    List<PropertyDto> properties = propertyService.findPropertyByZipCode(zipCode);
    return new ResponseEntity<>(properties, HttpStatus.OK);
  }

  //매물을 등록
  @PostMapping(path = "/properties")
  public ResponseEntity<Long> addProperty(@RequestBody PropertyDto propertyDto) {
    Long id = propertyService.enrollProperty(propertyDto);
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  //도로명주소에 해당하는 매물을 삭제
  @DeleteMapping(path = "/properties")
  public ResponseEntity<Void> deletePropertyByRoadNameAddress(
      @RequestParam("road-name-address") String roadNameAddress) {
    propertyService.deletePropertyByRoadNameAddress(roadNameAddress);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}