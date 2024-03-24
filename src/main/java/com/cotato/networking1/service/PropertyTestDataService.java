package com.cotato.networking1.service;

import com.cotato.networking1.domain.Property;
import com.cotato.networking1.repository.PropertyRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.transaction.Transactional;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyTestDataService {
    private PropertyRepository propertyRepository;

    @Transactional
    public String insertPropertyTestData(String path) throws InvalidFormatException, IOException {
    // 데이터를 엑셀 파일에서 읽어와 DB에 저장
        FileInputStream file = new FileInputStream("C:\\Users\\jooye\\cotato\\9th-BE-Networking-1\\src\\main\\resources\\property.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);
        // 하나의 시트만 존재하므로 임의로 0을 넣었음

        int numberOfRows = sheet.getPhysicalNumberOfRows();
        List<Property> propertyList = new ArrayList<Property>();

        for (int i = 1; i < numberOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            // i번째 행의 정보를 가져옴
            // id postcode road_address local_address
            // 기본키 우편번호 도로명주소 지번주소
            String postCode = row.getCell(0).getStringCellValue(); // 우편번호
            String province = row.getCell(1).getStringCellValue(); // 시도
            String district = row.getCell(2).getStringCellValue(); // 시군구
            String roadName = row.getCell(3).getStringCellValue();// 도로명
            String buildingNumMain = row.getCell(4).getStringCellValue(); // 건물번호 본번
            String buildingNumSub = row.getCell(5).getStringCellValue(); // 건물번호 부번
            String legalAddress = row.getCell(6).getStringCellValue(); // 법정동명
            String localNumMain = row.getCell(7).getStringCellValue(); // 지번본번
            String localNumSub = row.getCell(8).getStringCellValue(); // 지번부번

            String buildingNum = buildingNumSub.isEmpty() ? buildingNumMain : buildingNumMain + "-" + buildingNumSub;
            // 건물번호 부번이 null인 경우 공백 처리함

            String roadAddress = province + " " + district + " " + roadName + " " + buildingNum; // 도로명주소
            String localAddress = province + " " + district + " " + legalAddress + " " + localNumMain + "-" + localNumSub; // 지번 주소

            propertyList.add(Property.builder()
                    .postCode(Integer.valueOf(postCode))
                    .roadAddress(roadAddress)
                    .localAddress(localAddress)
                    .build());
        }

        propertyRepository.saveAll(propertyList);

        workbook.close();

        return "Success";
    }
}
