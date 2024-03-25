package com.cotato.networking1.service;


import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.domain.repository.PropertiesRepository;
import lombok.RequiredArgsConstructor;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertiesExcelService {

    private final PropertiesRepository propertiesRepository;

    @Transactional
    public String insertPropertyData(String path) {
        try {
            OPCPackage opcPackage = OPCPackage.open(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
            String sheetName = workbook.getSheetName(0);
            Sheet sheet = workbook.getSheet(sheetName);
            int n = sheet.getPhysicalNumberOfRows();
            List<Property> propertyList = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                Row row = sheet.getRow(i);
                String zipCode = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String district = row.getCell(2).getStringCellValue();
                String roadName = row.getCell(3).getStringCellValue();
                String buildingNum = String.valueOf((int) row.getCell(4).getNumericCellValue());
                String buildingNumSub = row.getCell(5) != null ? String.valueOf((int) row.getCell(5).getNumericCellValue()) : "";
                String area = row.getCell(6).getStringCellValue();

                String addressNum = String.valueOf((int) row.getCell(7).getNumericCellValue());
                String addressNumSub = String.valueOf((int) row.getCell(8).getNumericCellValue());
                String roadNameAddress = province + ' ' + district + ' ' + roadName + ' ' +
                        buildingNum + (buildingNumSub.equals("") ? "" : '-') + buildingNumSub;
                String landLotNameAddress = province + ' ' + district + ' ' + area + ' ' +
                        addressNum + '-' + addressNumSub;

                propertyList.add(Property.builder()
                        .zipCode(zipCode)
                        .roadNameAddress(roadNameAddress)
                        .landLotNameAddress(landLotNameAddress)
                        .build());
            }
            propertiesRepository.saveAll(propertyList);


            workbook.close();
            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to process the file: " + e.getMessage();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
            return "Invalid format: " + e.getMessage();
        }
    }
}
