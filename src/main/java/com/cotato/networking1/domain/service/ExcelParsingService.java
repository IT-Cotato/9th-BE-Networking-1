package com.cotato.networking1.domain.service;

import com.cotato.networking1.domain.entity.Property;
import com.cotato.networking1.domain.repository.PropertyRepository;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcelParsingService {

    private static final String EXCEL_PATH = "./properties.xlsx";
    private static final int ZIPCODE_CELL = 0;
    private static final int CITY_CELL = 1;
    private static final int CITY_DETAIL_CELL = 2;
    private static final int ROAD_NAME_CELL = 3;
    private static final int BUILDING_NAME_MAIN_CELL = 4;
    private static final int BUILDING_NAME_SUB_CELL = 5;
    private static final int DONG_NAME_CELL = 6;
    private static final int LOT_MAIN_CELL = 7;
    private static final int LOT_SUB_CELL = 8;
    private final PropertyRepository propertyRepository;

    @Transactional
    public void createData() throws InvalidFormatException, IOException {
        OPCPackage excel = OPCPackage.open(new File(EXCEL_PATH));
        Workbook sheets = new XSSFWorkbook(excel);
        Sheet firstSheet = sheets.getSheetAt(0);

        for (Row row : firstSheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String zipCode = getZipCode(row);
            String basicAddress = getBasicAddress(row);
            String roadNameAddress = basicAddress + " " + getRoadNameAddress(row);
            String landLotNameAddress = basicAddress + " " + getLandLotNameAddress(row);
            log.info("[기본 주소]: {}", basicAddress);

            Property createdProperty = Property.of(zipCode, roadNameAddress, landLotNameAddress);
            propertyRepository.save(createdProperty);
        }
        sheets.close();
    }

    private String getLandLotNameAddress(Row row) {
        Cell dong = row.getCell(DONG_NAME_CELL);
        Cell lotMain = row.getCell(LOT_MAIN_CELL);
        Cell lotSub = row.getCell(LOT_SUB_CELL);
        return dong.getStringCellValue() + " " + (int) lotMain.getNumericCellValue() + "-"
                + (int) lotSub.getNumericCellValue();
    }

    private String getRoadNameAddress(Row row) {
        String road = row.getCell(ROAD_NAME_CELL).getStringCellValue();
        int buildingMain = (int) row.getCell(BUILDING_NAME_MAIN_CELL).getNumericCellValue();
        Cell buildingSubCell = row.getCell(BUILDING_NAME_SUB_CELL);

        if (buildingSubCell == null) {
            return road + " " + buildingMain;
        }
        int buildingSub = (int) buildingSubCell.getNumericCellValue();
        return road + " " + buildingMain + "-" + buildingSub;
    }

    private String getBasicAddress(Row row) {
        Cell city = row.getCell(CITY_CELL);
        Cell details = row.getCell(CITY_DETAIL_CELL);
        return city.getStringCellValue() + " " + details.getStringCellValue();
    }

    private String getZipCode(Row row) {
        Cell cell = row.getCell(ZIPCODE_CELL);
        return cell.getStringCellValue();
    }
}
