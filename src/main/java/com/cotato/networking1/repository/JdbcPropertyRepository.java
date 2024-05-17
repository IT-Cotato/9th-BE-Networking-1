package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcPropertyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAllByJdbc(List<Property> propertyList){
        String sql = "INSERT INTO property (zipCode, roadNameAddress, landLotNameAddress) values (?,?,?)";
        jdbcTemplate.batchUpdate(sql,
                propertyList,
                propertyList.size(),
                (PreparedStatement ps, Property p) -> {
                    ps.setString(1, p.getZipCode());
                    ps.setString(2, p.getRoadNameAddress());
                    ps.setString(3, p.getLandLotNameAddress());
                });
    }
}
