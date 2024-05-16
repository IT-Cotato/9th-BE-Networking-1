package com.cotato.networking1.repository;

import com.cotato.networking1.entity.Property;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PropertyJdbcRepository {

  private final JdbcTemplate jdbcTemplate;

  public void saveAll(List<Property> properties) {
    String query = "INSERT INTO property(zip_code, road_name_address, land_lot_name_address) VALUES (?, ?, ?)";
    jdbcTemplate.batchUpdate(
        query,
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            Property property = properties.get(i);
            ps.setString(1, property.getZipCode());
            ps.setString(2, property.getRoadNameAddress());
            ps.setString(3, property.getLandLotNameAddress());
          }

          @Override
          public int getBatchSize() {
            return properties.size();
          }
        }
    );
  }

//  public void deleteAll() {
//    String selectQuery = "SELECT zip_code FROM property";
//    List<String> zipCodes = jdbcTemplate.queryForList(selectQuery, String.class);
//
//    String deleteQuery = "DELETE FROM property WHERE zip_code = ?";
//    jdbcTemplate.batchUpdate(
//        deleteQuery,
//        new BatchPreparedStatementSetter() {
//          @Override
//          public void setValues(PreparedStatement ps, int i) throws SQLException {
//            String zipCode = zipCodes.get(i);
//            ps.setString(1, zipCode);
//          }
//
//          @Override
//          public int getBatchSize() {
//            return zipCodes.size();
//          }
//        }
//    );
//  }

}

