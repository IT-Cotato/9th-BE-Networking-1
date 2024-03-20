package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

  List<Property> findByRoadNameAddress(String roadNameAddress);
}
