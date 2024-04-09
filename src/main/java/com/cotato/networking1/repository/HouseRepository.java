package com.cotato.networking1.repository;

import com.cotato.networking1.enity.HouseDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<HouseDao, Long> {
    Optional<HouseDao> findById(Long id);

}
