package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, CrudRepository<Property, Long> {

    List<Property> findAllByZipCode(String zipCode);

    List<Property> findAllByRoadNameAddress(String roadNameAddress);

    @Modifying
    @Query("DELETE FROM Property p WHERE p.id IN :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);
}
