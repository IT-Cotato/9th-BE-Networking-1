package com.cotato.networking1.service;

import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelService {

    private final PropertyRepository propertyRepository;

    @Transactional
    public void parsingTestData(MultipartFile multipartFile) throws IOException{

        List<String> propertyList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        //첫 번째 시트 불러오기
        Sheet worksheet = workbook.getSheetAt(0);

        for(int i = 1 ; i < worksheet.getPhysicalNumberOfRows() ; i++){
            //i번째 행 정보 가져오기
            Row row = worksheet.getRow(i);

            if(row != null){

                String zipCode = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String county = row.getCell(2).getStringCellValue();
                String roadName = row.getCell(3).getStringCellValue();
                String buildingNumber = String.valueOf((int)row.getCell(4).getNumericCellValue());
                String buildingNumberSub;
                if(row.getCell(5) != null){
                    buildingNumberSub = String.valueOf((int)row.getCell(5).getNumericCellValue());
                }else{
                    buildingNumberSub = "";
                }
                String town = row.getCell(6).getStringCellValue();
                String landLotNum = String.valueOf((int)row.getCell(7).getNumericCellValue());
                String landLotNumSub = String.valueOf((int)row.getCell(8).getNumericCellValue());

                String roadNameAddress = province + " " + county + " " + roadName + " "
                        + buildingNumber + "-" + buildingNumberSub;
                String landLotNameAddress = province + " " + county + " " + town + " "
                        + landLotNum + "-" + landLotNumSub;

                Property property = Property.builder()
                        .zipCode(zipCode)
                        .roadNameAddress(roadNameAddress)
                        .landLotNameAddress(landLotNameAddress)
                        .build();
                propertyRepository.save(property);
            }
        }
        workbook.close();
    }
}
