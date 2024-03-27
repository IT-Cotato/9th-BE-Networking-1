package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertiesRepository extends JpaRepository<Property, Long> {
    List<Property> findByZipCode(String zipCode);
    Property findByRoadNameAddress(String roadNameAddress);
}