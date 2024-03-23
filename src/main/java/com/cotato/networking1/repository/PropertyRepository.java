package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByZipCode(String zipCode);

    List<Property> findByRoadAddress(String roadAddress);

}
