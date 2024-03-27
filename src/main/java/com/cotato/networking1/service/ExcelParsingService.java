package com.cotato.networking1.service;

import com.cotato.networking1.entity.Property;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ExcelParsingService {

    private final PropertiesService propertiesService;

    public void parseExcelFile(MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String zipCode = getCellValue(row, 0);
                String roadNameAddress = getCellValue(row, 1);
                String landLotNameAddress = getCellValue(row, 2);

                Property property = Property.of(zipCode, roadNameAddress, landLotNameAddress);
                propertiesService.save(property);
            }
        }
    }

    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        return cell != null ? cell.toString() : null;
    }
}