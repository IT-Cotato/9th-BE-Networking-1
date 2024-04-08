package com.cotato.networking1.controller;

import com.cotato.networking1.domain.dto.RealEstateListResponse;
import com.cotato.networking1.domain.dto.RealEstateRegisterRequest;
import com.cotato.networking1.domain.dto.RealEstateRegisterResponse;
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
    public ResponseEntity<RealEstateListResponse> getAllByZipCode(@RequestParam("zip-code") String zipCode){
        return ResponseEntity.status(HttpStatus.OK).body(realEstateService.getAllByZipCode(zipCode));
    }

    @PostMapping()
    public ResponseEntity<RealEstateRegisterResponse> registerEstate(@RequestBody RealEstateRegisterRequest realEstateRegisterRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(realEstateService.registerEstate(realEstateRegisterRequest));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteEstate(@RequestParam("road-name-address") String roadNameAddress){
        realEstateService.deleteByRoadNameAddress(roadNameAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
