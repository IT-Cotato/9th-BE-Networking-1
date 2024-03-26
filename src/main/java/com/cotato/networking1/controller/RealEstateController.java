package com.cotato.networking1.controller;

import com.cotato.networking1.domain.dto.RequestDTO;
import com.cotato.networking1.domain.dto.ResponseDTO;
import com.cotato.networking1.domain.dto.RealEstateListDTO;
import com.cotato.networking1.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/properties")
public class RealEstateController {

    private final RealEstateService realEstateService;

    @GetMapping()
    public ResponseEntity<RealEstateListDTO> getAllByZipCode(@RequestParam("zip-code") String zipCode){
        return ResponseEntity.status(HttpStatus.OK).body(realEstateService.getAllByZipCode(zipCode));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> registerEstate(@RequestBody RequestDTO realestate){
        return ResponseEntity.status(HttpStatus.CREATED).body(realEstateService.registerEstate(realestate));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteEstate(@RequestParam("road-name-address") String roadNameAddress){
        realEstateService.deleteEstate(roadNameAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
