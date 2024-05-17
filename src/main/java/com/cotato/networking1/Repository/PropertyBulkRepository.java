package com.cotato.networking1.Repository;

import com.cotato.networking1.Entity.Property;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PropertyBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<Property> propertyList) {
        String sql = "insert into property (land_lot_name_address, road_name_address, zip_code)" +
                "values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                propertyList,
                propertyList.size(),
                (PreparedStatement ps, Property property) -> {
            ps.setString(1, property.getLandLotNameAddress());
            ps.setString(2, property.getRoadNameAddress());
            ps.setString(3, property.getZipCode());
                });
    }
}
