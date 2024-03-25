package com.cotato.networking1.domain.repository;

import com.cotato.networking1.domain.entity.Property;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByZipCode(String zipCode);

    @Transactional
    @Modifying
    @Query("delete from Property p where p.id in :ids")
    void deleteAllByRoadNameAddressInQuery(@Param("ids") List<Long> ids);

    List<Property> findAllByRoadNameAddress(String roadNameAddress);
}
