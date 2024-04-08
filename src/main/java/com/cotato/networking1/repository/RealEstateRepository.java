package com.cotato.networking1.repository;

import com.cotato.networking1.domain.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    public List<RealEstate> findAllByZipCode(String zipCode);
    public void deleteByRoadNameAddress(String roadNameAddress);
}
