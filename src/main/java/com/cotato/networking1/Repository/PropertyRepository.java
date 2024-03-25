package com.cotato.networking1.Repository;

import com.cotato.networking1.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByZipCode(String zipCode);
}
