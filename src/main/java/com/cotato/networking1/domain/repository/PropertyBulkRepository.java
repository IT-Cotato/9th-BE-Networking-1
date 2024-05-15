package com.cotato.networking1.domain.repository;

import com.cotato.networking1.domain.entity.Property;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PropertyBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Property> properties) {
        String sql =
                "INSERT INTO property (property_zipcode, property_road_name_address, property_land_lot_name_address)" +
                        "VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                properties,
                properties.size(),
                (PreparedStatement ps, Property property) -> {
                    ps.setString(1, property.getZipCode());
                    ps.setString(2, property.getRoadNameAddress());
                    ps.setString(3, property.getLandLotNameAddress());
                });
    }
}
