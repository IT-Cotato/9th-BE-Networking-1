package com.cotato.networking1.service;

import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public void saveProperties(MultipartFile file) throws IOException {
        List<Property> properties = parseExcelFile(file);
        propertyRepository.saveAll(properties);
    }

    private List<Property> parseExcelFile(MultipartFile file) throws IOException {
        List<Property> properties = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // 헤더 건너뛰기
            Property property = new Property();
            property.setZipCode(row.getCell(0).getStringCellValue());
            property.setRoadAddress(
                    row.getCell(1).getStringCellValue() + " " +
                    row.getCell(2).getStringCellValue() + " " +
                    row.getCell(3).getStringCellValue() + " " +
                    numericToString(row.getCell(4)) + "-" +
                    numericToString(row.getCell(5)));
            property.setLotNumberAddress(
                    row.getCell(1).getStringCellValue() + " " +
                    row.getCell(2).getStringCellValue() + " " +
                    row.getCell(6).getStringCellValue() + " " +
                    numericToString(row.getCell(7)) + "-" +
                    numericToString(row.getCell(8)));
            properties.add(property);
        }
        return properties;
    }

    private String numericToString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return ""; // 셀이 null이거나 비어 있으면 빈 문자열을 반환
        }
        // 셀 타입 확인 후 적절한 처리
        if (cell.getCellType() == CellType.NUMERIC) {
            double numericValue = cell.getNumericCellValue();
            long longValue = (long) numericValue; // 숫자를 정수로 변환
            return String.valueOf(longValue); // 변환된 값을 문자열로 반환
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue(); // 문자열 셀이면 그대로 반환
        }
        return ""; // 그 외 셀 타입은 빈 문자열 반환 또는 다른 로직 적용
    }


//    //row의 cell을 받아와서 numeric value를 String으로 바꾸어주는 함수
//    private String numericToString(Cell cell) {
//        double numericValue = cell.getNumericCellValue();
//        int intValue = (int) numericValue;
//        return Integer.toString(intValue);
//    }

    public List<Property> findPropertiesByZipCode(String zipCode) {
        return propertyRepository.findByZipCode(zipCode);
    }

}
