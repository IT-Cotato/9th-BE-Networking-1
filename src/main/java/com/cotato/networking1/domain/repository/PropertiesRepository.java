package com.cotato.networking1.domain.repository;


import com.cotato.networking1.domain.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Property, Long> {


    List<Property> findByZipCode(String zipCode);

    Property findByRoadNameAddress(String roadNameAddress);
}

