package com.cotato.networking1.service;

import com.cotato.networking1.domain.enttiy.Property;
import com.cotato.networking1.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
public class PropertyTestDataService {
    private final PropertyRepository propertyRepository;

    @Transactional
    public String insertPropertyTestData(String path) throws InvalidFormatException, IOException {
        OPCPackage opcPackage = OPCPackage.open(new File(path));
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);

        String sheetName = workbook.getSheetName(0);
        Sheet sheet = workbook.getSheet(sheetName);

        int n = sheet.getPhysicalNumberOfRows();

        List<Property> propertyList = new ArrayList<Property>();

        for (int i = 1; i < n; i++) {
            Row row = sheet.getRow(i);

            String zipCode = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String district = row.getCell(2).getStringCellValue();

            String roadName = row.getCell(3).getStringCellValue();
            String buildingNum = String.valueOf((int) row.getCell(4).getNumericCellValue());
            String buildingNumSub;
            if (row.getCell(5) != null) {
                buildingNumSub = String.valueOf((int) row.getCell(5).getNumericCellValue());
            } else {
                buildingNumSub = "";
            }

            String area = row.getCell(6).getStringCellValue();
            String addressNum = String.valueOf((int) row.getCell(7).getNumericCellValue());
            String addressNumSub = String.valueOf((int) row.getCell(8).getNumericCellValue());

            String roadNameAddress = new StringBuilder()
                    .append(province).append(' ')
                    .append(district).append(' ')
                    .append(roadName).append(' ')
                    .append(buildingNum).append(buildingNumSub.equals("") ? "" : '-')
                    .append(buildingNumSub)
                    .toString();

            String landLotNameAddress = new StringBuilder()
                    .append(province).append(' ')
                    .append(district).append(' ')
                    .append(area).append(' ')
                    .append(addressNum).append('-')
                    .append(addressNumSub)
                    .toString();

            propertyList.add(Property.builder()
                    .zipCode(zipCode)
                    .roadNameAddress(roadNameAddress)
                    .landLotNameAddress(landLotNameAddress)
                    .build());
        }

        propertyRepository.saveAll(propertyList);

        workbook.close();
        opcPackage.close();
        return "Success";
    }
}
