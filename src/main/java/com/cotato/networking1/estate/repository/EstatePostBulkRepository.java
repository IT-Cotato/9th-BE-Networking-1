package com.cotato.networking1.estate.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EstatePostBulkRepository {

	private final JdbcTemplate jdbcTemplate;

	@Transactional
	public void saveAll(List<List<String>> dataList){
		String sql = "INSERT INTO estate (zip_code, road_name_address, land_lot_name_address)"+
			"VALUES (?, ?, ?)";

		jdbcTemplate.batchUpdate(sql,
			dataList,
			dataList.size(),
			(PreparedStatement ps, List<String> data) -> {
				ps.setString(1, data.get(0));
				ps.setString(2, data.get(1));
				ps.setString(3, data.get(2));
			});
	}
}
