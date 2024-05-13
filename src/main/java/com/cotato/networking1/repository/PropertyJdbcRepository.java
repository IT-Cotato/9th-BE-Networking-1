package com.cotato.networking1.repository;

import com.cotato.networking1.domain.enttiy.Property;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PropertyJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    @Transactional
    public void saveAll(List<Property> propertyList) {
        String sql = "INSERT INTO property (zip_code, road_name_address, land_lot_name_address) " +
                "VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                propertyList,
                propertyList.size(),
                (PreparedStatement ps, Property property) -> {
                    ps.setString(1, property.getZipCode());
                    ps.setString(2, property.getRoadNameAddress());
                    ps.setString(3, property.getLandLotNameAddress());
                });
    }
}
