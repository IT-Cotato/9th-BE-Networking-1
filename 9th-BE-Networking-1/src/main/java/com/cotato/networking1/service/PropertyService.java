package com.cotato.networking1.service;

import com.cotato.networking1.domain.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PropertyService {
    private PropertyRepository propertyRepository;

    public void savePropertiesToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Property> properties = ExcelUploadService.getPropertiesDataFromExcel(file.getInputStream());
                this.propertyRepository.saveAll(properties);
            } catch (IOException e){
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
    public List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    public List<Property> getPropertiesByZipCode(String zipCode){
        return propertyRepository.findAllByZipCode(zipCode);
    }
}
