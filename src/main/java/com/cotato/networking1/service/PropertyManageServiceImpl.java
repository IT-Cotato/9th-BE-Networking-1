package com.cotato.networking1.service;

import com.cotato.networking1.dto.PropertyDto;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PropertyManageServiceImpl implements PropertyService {

  private final PropertyRepository repository;

  @Autowired
  public PropertyManageServiceImpl(PropertyRepository repository) {
    this.repository = repository;
  }

  @Override
  public void upload(MultipartFile file) {



  }


  public List<PropertyDto> findPropertyByZipCode(int zipCode) {
    List<Property> properties = repository.findAll();
    List<PropertyDto> propertiesDtoList = new ArrayList<>();

    for (Property property : properties) {
      if (property.getZipCode().equals(zipCode)) {
        PropertyDto propertiesDto = PropertyDto.builder()
            .id(property.getId())
            .zipCode(property.getZipCode())
            .roadNameAddress(property.getLandLotNameAddress())
            .landLotNameAddress(property.getLandLotNameAddress())
            .build();
        propertiesDtoList.add(propertiesDto);
      }
    }
    return propertiesDtoList;
  }

  @Transactional
  @Override
  public Long enrollProperty(PropertyDto propertyDto) {
    List<Property> properties = repository.findAll();
    return repository.save(propertyDto.toEntity()).getId();
  }

  @Transactional
  @Override
  public void deletePropertyByRoadNameAddress(String roadNameAddress) {
    List<Property> properties = repository.findAll();
    for (Property property : properties) {
      if (property.getRoadNameAddress().equals(roadNameAddress)) {
        PropertyDto propertiesDto = PropertyDto.builder()
            .id(property.getId())
            .zipCode(property.getZipCode())
            .roadNameAddress(property.getLandLotNameAddress())
            .landLotNameAddress(property.getLandLotNameAddress())
            .build();
        repository.deleteById(propertiesDto.getId());
      }
    }
  }
}