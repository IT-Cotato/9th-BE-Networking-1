package com.cotato.networking1.service;

import com.cotato.networking1.dto.PropertyDto;
import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PropertyManageServiceImpl implements PropertyService {

  private final PropertyRepository repository;

  @Autowired
  public PropertyManageServiceImpl(PropertyRepository repository) {
    this.repository = repository;
  }

  public List<PropertyDto> findPropertyByZipCode(String zipCode) {
    List<Property> properties = repository.findAll();
    List<PropertyDto> propertiesDtoList = new ArrayList<>();
    for (Property property : properties) {
      if (property.getZipCode().equals(zipCode)) {
        PropertyDto propertiesDto = PropertyDto.builder()
            .id(property.getId())
            .zipCode(property.getZipCode())
            .roadNameAddress(property.getRoadNameAddress())
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
    return repository.save(propertyDto.toEntity()).getId();
  }

  @Transactional
  @Override
  public ResponseEntity<String> deletePropertyByRoadNameAddress(String roadNameAddress) {
    List<Property> properties = repository.findByRoadNameAddress(roadNameAddress);
    if (properties.isEmpty()) {
      return new ResponseEntity<>("Property NOT FOUND!!", HttpStatus.NOT_FOUND);
    }
    repository.deleteAll(properties);
    return new ResponseEntity<>("Delete Complete!!", HttpStatus.OK);
  }

}