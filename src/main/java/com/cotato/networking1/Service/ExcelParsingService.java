package com.cotato.networking1.Service;

import com.cotato.networking1.Entity.Property;
import com.cotato.networking1.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelParsingService {

    private final PropertyRepository propertyRepository;
    public static final String FILE_PATH = "C:\\Users\\samsung\\Desktop\\properties.xlsx";

    public void upload() throws InvalidFormatException, IOException {
        OPCPackage opcPackage = OPCPackage.open(new File(FILE_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        String sheetName = workbook.getSheetName(0);
        Sheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        List<Property> propertyList = new ArrayList<>();

        for(int rowIndex = 1; rowIndex < rows; rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            String postalCode = row.getCell(0).getStringCellValue();
            String city = row.getCell(1).getStringCellValue();
            String district = row.getCell(2).getStringCellValue();
            String roadName = row.getCell(3).getStringCellValue();
            String buildingNum = String.valueOf((int) row.getCell(4).getNumericCellValue());

            String buildingNumSub;
            if(row.getCell(5) != null){
                buildingNumSub = String.valueOf((int) row.getCell(5).getNumericCellValue());
            }
            else{
                buildingNumSub = "";
            }

            String dong = row.getCell(6).getStringCellValue();
            String landLotNum = String.valueOf((int) row.getCell(7).getNumericCellValue());
            String landLotNumSub = String.valueOf((int) row.getCell(8).getNumericCellValue());

            String roadNameAddress = city + " " + district + " " + roadName + " " + buildingNum
                    + (buildingNumSub.equals("") ? "" : "-") + buildingNumSub;
            String landLotNumAddress = city + " " + district + " " + dong + " " + landLotNum + "-" + landLotNumSub;

            propertyList.add(Property.builder()
                    .postalCode(postalCode)
                    .roadNameAddress(roadNameAddress)
                    .landLotNumAddress(landLotNumAddress)
                    .build());
        }
        propertyRepository.saveAll(propertyList);

        workbook.close();
    }

}
