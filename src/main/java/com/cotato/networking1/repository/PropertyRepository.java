package com.cotato.networking1.repository;

import com.cotato.networking1.domain.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    public List<Property> findAllByZipCode(String zipCode);
    public void deleteByRoadNameAddress(String roadNameAddress);
}