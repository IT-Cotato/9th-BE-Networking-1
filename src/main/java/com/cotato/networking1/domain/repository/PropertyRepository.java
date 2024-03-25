package com.cotato.networking1.domain.repository;

import com.cotato.networking1.domain.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
