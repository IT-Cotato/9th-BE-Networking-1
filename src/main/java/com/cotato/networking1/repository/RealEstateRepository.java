package com.cotato.networking1.repository;

import com.cotato.networking1.domain.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    List<RealEstate> getAllByZipCode(String zipCode);
    List<RealEstate> findAllByRoadNameAddress(String roadNameAddress);
}
