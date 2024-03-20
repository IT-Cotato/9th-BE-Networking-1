package com.cotato.networking1.estate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cotato.networking1.estate.domain.Estate;

public interface EstateRepository  extends JpaRepository<Estate, Long> {

	List<Estate> findAllByZipCode(int zipCode);

	List<Estate> findAllByRoadNameAddress(String roadNameAddress);
}
