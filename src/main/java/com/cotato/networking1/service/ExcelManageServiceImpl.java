package com.cotato.networking1.service;

import com.cotato.networking1.entity.Property;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelManageServiceImpl implements ExcelManageService {

  @Override
  public void upload(MultipartFile file) {

    ArrayList<Property> properties = new ArrayList<>();
    // 파일 Original 이름 불러오기 ex) 전문가.xlsx
    String fileExtsn = FilenameUtils.getExtension(file.getOriginalFilename());

    Workbook workbook = null;

    try {
      // 엑셀 97 - 2003 까지는 HSSF(xls),  엑셀 2007 이상은 XSSF(xlsx)
      if (fileExtsn.equals("xls")) {
        workbook = new HSSFWorkbook(file.getInputStream());
      } else {
        workbook = new XSSFWorkbook(file.getInputStream());
      }

      // 엑셀파일에서 첫번째 시트 불러오기
      Sheet worksheet = workbook.getSheetAt(0);
      // getPhysicalNumberOfRow 는 행의 갯수를 불러오는 매소드
      for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
        // i번째 행 정보 가져오기
        Row row = worksheet.getRow(i);
        if (row != null) {
          for (int j = 1; j <= 9; j++) {
            // i번째 행의 j번째 셀 정보 가져오기
            Cell cell = row.getCell(j);
            String value = formatCellData(cell);
            int zipCode = 0;
            StringBuilder roadNameAddress = new StringBuilder();
            StringBuilder landLotNameAddress = new StringBuilder();
            if (value != null) {
              if (j == 1) {
                zipCode = Integer.parseInt(value);
              } else if (j == 2 || j == 3) {
                roadNameAddress.append(value).append(" ");
                landLotNameAddress.append(value).append(" ");
              } else if (j == 4) {
                landLotNameAddress.append(value).append(" ");
              } else if (j == 5) {
                landLotNameAddress.append(value);
              } else if (j == 6) {
                landLotNameAddress.append("-").append(value);
              } else if (j == 7) {
                roadNameAddress.append(value).append(" ");
              } else if (j == 8) {
                roadNameAddress.append(value);
              } else { //roadNameAddress 정의
                if (!value.isEmpty()) {
                  roadNameAddress.append("-").append(value);
                }
                properties.add(Property.builder()
                    .id((long) i)
                    .zipCode(zipCode)
                    .roadNameAddress(roadNameAddress.toString())
                    .landLotNameAddress(landLotNameAddress.toString())
                    .build());
              }
            }
          }
        }
      }
      workbook.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  //셀 데이터 형식 포맷
  private static String formatCellData(Cell cell) {
    String value = "";
    if (cell == null) {
      value = null;
    } else {
      // 타입별로 내용 읽기
      switch (cell.getCellType()) {
        case FORMULA:
          value = cell.getCellFormula();
          break;
        case NUMERIC:
          double numericCellValue = cell.getNumericCellValue();
          value = String.valueOf(numericCellValue);
          if (numericCellValue == Math.rint(numericCellValue)) {
            value = String.valueOf((int) numericCellValue);
          } else {
            value = String.valueOf(numericCellValue);
          }
          break;
        case STRING:
          value = cell.getStringCellValue() + "";
          break;
        case BLANK:
          value = cell.getBooleanCellValue() + "";
          break;
        case ERROR:
          value = cell.getErrorCellValue() + "";
          break;
        default:
          value = cell.getStringCellValue();
          break;
      }
    }
    return value;
  }

}

