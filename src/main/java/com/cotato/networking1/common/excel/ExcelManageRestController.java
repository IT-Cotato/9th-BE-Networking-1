package com.cotato.networking1.common.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cotato.networking1.common.dto.DataResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExcelManageRestController {

	private final ExcelManageServiceImpl excelManageServiceImpl;

	@GetMapping("/test-data")
	public DataResponse upload() {
		return excelManageServiceImpl.upload();
	}
}
