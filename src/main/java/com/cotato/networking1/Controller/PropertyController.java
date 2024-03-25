package com.cotato.networking1.Controller;

import com.cotato.networking1.DTO.Request.PropertyCreateDTO;
import com.cotato.networking1.DTO.Response.PropertyCreateResponseDTO;
import com.cotato.networking1.DTO.Response.PropertyListResponseDTO;
import com.cotato.networking1.DTO.Response.PropertyResponseDTO;
import com.cotato.networking1.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public PropertyListResponseDTO getList(@RequestParam("zip-code") String zipCode){
        List<PropertyResponseDTO> all = propertyService.getAllByZipCode(zipCode);
        return new PropertyListResponseDTO(all);
    }

    @PostMapping
    public PropertyCreateResponseDTO create(@RequestBody PropertyCreateDTO propertyCreateDTO){
        return propertyService.createProperty(propertyCreateDTO);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("road-name-address") String roadNameAddress){
        propertyService.deleteByRoadNameAddress(roadNameAddress);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
