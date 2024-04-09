package com.cotato.networking1.controller;


import com.cotato.networking1.enity.HouseDao;
import com.cotato.networking1.model.HouseDto;
import com.cotato.networking1.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("house/api/")
public class HouseRepository {

    private final HouseService houseService;


    @PostMapping("crate")
    public ResponseEntity<HouseDto> crateHouse(@RequestBody HouseDto houseDto){
        HouseDto houseCreateInformation = houseService.houseCreateInformation(houseDto);
        return new ResponseEntity<>(houseCreateInformation, HttpStatus.CREATED);
    }

    @GetMapping("info/{id}")
    public ResponseEntity<HouseDto> infoHouse(@PathVariable Long id){
        HouseDto houseInfo = houseService.houseInfo(id);
        return new ResponseEntity<>(houseInfo, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<HouseDto> upHouse(@PathVariable Long id, @RequestBody HouseDto houseDto){
        houseService.houseInformationUpdate(id, houseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id){
        houseService.houseRemove(id);
        return ResponseEntity.noContent().build();
    }
}
