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
import java.util.stream.Collectors;

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
            if (row.getRowNum() == 0) continue;

            Property property = new Property();
            property.setZipCode(row.getCell(0).getStringCellValue());

            StringBuilder roadAddress = new StringBuilder();
            roadAddress.append(row.getCell(1).getStringCellValue()).append(" ")
                    .append(row.getCell(2).getStringCellValue()).append(" ")
                    .append(row.getCell(3).getStringCellValue()).append(" ")
                    .append(numericToString(row.getCell(4))).append("-")
                    .append(numericToString(row.getCell(5)));
            property.setRoadAddress(roadAddress.toString());

            StringBuilder lotNumberAddress = new StringBuilder();
            lotNumberAddress.append(row.getCell(1).getStringCellValue()).append(" ")
                    .append(row.getCell(2).getStringCellValue()).append(" ")
                    .append(row.getCell(6).getStringCellValue()).append(" ")
                    .append(numericToString(row.getCell(7))).append("-")
                    .append(numericToString(row.getCell(8)));
            property.setLotNumberAddress(lotNumberAddress.toString());

            properties.add(property);
        }
        return properties;
    }

    private String numericToString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            throw new IllegalArgumentException("Cell is null or blank");
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            double numericValue = cell.getNumericCellValue();
            long longValue = (long) numericValue;
            return String.valueOf(longValue);
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                double numericValue = Double.parseDouble(cell.getStringCellValue());
                long longValue = (long) numericValue;
                return String.valueOf(longValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("String cell contains non-numeric value");
            }
        } else {
            throw new IllegalArgumentException("Unsupported cell type: " + cell.getCellType());
        }
    }

    public List<Property> findPropertiesByZipCode(String zipCode) {
        return propertyRepository.findByZipCode(zipCode);
    }

    public List<Long> deletePropertiesByRoadAddress(String roadAddress) {
        List<Property> properties = propertyRepository.findByRoadAddress(roadAddress);
        List<Long> deletedIds = properties.stream()
                .map(Property::getId)
                .collect(Collectors.toList()); // 삭제될 매물의 ID 수집
        propertyRepository.deleteAll(properties); // 매물 삭제
        return deletedIds; // 삭제된 매물의 ID 반환
    }


}
