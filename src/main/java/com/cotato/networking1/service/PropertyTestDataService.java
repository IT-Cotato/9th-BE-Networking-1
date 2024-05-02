package com.cotato.networking1.service;

import com.cotato.networking1.domain.Property;
import com.cotato.networking1.repository.PropertyRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PropertyTestDataService {
    private PropertyRepository propertyRepository;

    public PropertyTestDataService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Transactional
    public String insertPropertyTestData(String path) throws InvalidFormatException, IOException {
    // 데이터를 엑셀 파일에서 읽어와 DB에 저장
        FileInputStream file = new FileInputStream("C:\\Users\\jooye\\cotato\\9th-BE-Networking-1\\src\\main\\resources\\propertyFile.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);
        // 하나의 시트만 존재하므로 임의로 0을 넣었음

        int numberOfRows = sheet.getPhysicalNumberOfRows();
        List<Property> propertyList = new ArrayList<Property>();

        log.info("Reading data from Excel file at path: {}", path); // 파일 읽기 시작 로그

        try {
            log.info("try문 실행");
            for (int i = 1; i < numberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                // i번째 행의 정보를 가져옴
                // id postcode road_address local_address
                // 기본키 우편번호 도로명주소 지번주소
                Property property = new Property();

                String postCode = row.getCell(0).getStringCellValue();// 도로명

                String province = row.getCell(1).getStringCellValue(); // 시도
                String district = row.getCell(2).getStringCellValue(); // 시군구
                String roadName = row.getCell(3).getStringCellValue();// 도로명
                String buildingNumMain = String.valueOf((int) row.getCell(4).getNumericCellValue()); // 건물번호 본번
                String buildingNumSub = "";
                if (row.getCell(5) != null) { // 건물번호 부번이 있는 경우
                    buildingNumSub = String.valueOf((int) row.getCell(5).getNumericCellValue());
                }
                String legalAddress = row.getCell(6).getStringCellValue(); // 법정동명
                String localNumMain = String.valueOf((int) row.getCell(7).getNumericCellValue()); // 지번본번
                String localNumSub = String.valueOf((int) row.getCell(8).getNumericCellValue()); // 지번부번

                String roadAddress = province + " " + district + " " + roadName + " " + buildingNumMain + (buildingNumSub.equals("") ? " " : '-') + buildingNumSub; // 도로명주소
                String localAddress = province + " " + district + " " + legalAddress + " " + localNumMain + "-" + localNumSub; // 지번 주소

                propertyList.add(Property.builder()
                        .postCode(postCode)
                        .roadAddress(roadAddress)
                        .localAddress(localAddress)
                        .build());
            }

            propertyRepository.saveAll(propertyList);
            workbook.close();

            return "엑셀 파싱을 성공하였습니다!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
