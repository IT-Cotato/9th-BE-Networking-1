package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByZipCode(String zipCode);
    void deleteByRoadNameAddress(String roadNameAddress);
}
