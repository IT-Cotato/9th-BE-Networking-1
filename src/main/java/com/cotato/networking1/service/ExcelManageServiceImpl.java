package com.cotato.networking1.service;

import com.cotato.networking1.entity.Property;
import com.cotato.networking1.repository.PropertyJdbcRepository;
import com.cotato.networking1.repository.PropertyRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExcelManageServiceImpl implements ExcelManageService {


  private final PropertyRepository propertyRepository;
  private final PropertyJdbcRepository propertyJdbcRepository;
  private final static String NETWORKING_1_FILEPATH = "C:\\Users\\USER\\Desktop\\Cotato\\9th-BE-Networking-1\\src\\main\\resources\\서울시_20만개.xlsx";
  private final static String NETWORKING_3_FILEPATH = "C:\\Users\\USER\\Desktop\\Cotato\\9th-BE-Networking-1\\src\\main\\resources\\서울시_20만개.xlsx";
  public ExcelManageServiceImpl(PropertyRepository propertyRepository, PropertyJdbcRepository propertyJdbcRepository) {
    this.propertyRepository = propertyRepository;
    this.propertyJdbcRepository = propertyJdbcRepository;
  }

  @Transactional
  @Override
  public void jpaUpload() throws IOException {
    List<Property> properties = new ArrayList<>();
    FileInputStream file = new FileInputStream(NETWORKING_1_FILEPATH);
    XSSFWorkbook workbook = new XSSFWorkbook(file);
    try {
      // 엑셀파일에서 첫번째 시트 불러오기
      Sheet worksheet = workbook.getSheetAt(0);
      // getPhysicalNumberOfRow 는 행의 갯수를 불러오는 매소드
      for (int rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
        // i번째 행 정보 가져오기
        Row row = worksheet.getRow(rowIndex);

        String zipCode = "";
        StringBuilder roadNameAddress = new StringBuilder();
        StringBuilder landLotNameAddress = new StringBuilder();
        if (row != null) {
          for (int columnIndex = 0; columnIndex <= 9; columnIndex++) {
            // i번째 행의 j번째 셀 정보 가져오기
            Cell cell = row.getCell(columnIndex);
            String value = formatCellData(cell);
            if (value != null) {
              if (columnIndex == 0) {
                zipCode = value;
              } else if (columnIndex == 1 || columnIndex == 2) {
                landLotNameAddress.append(value).append(" ");
                roadNameAddress.append(value).append(" ");
              } else if (columnIndex == 3) {
                roadNameAddress.append(value).append(" ");
              } else if (columnIndex == 4) {
                roadNameAddress.append(value);
              } else if (columnIndex == 5) {
                roadNameAddress.append("-").append(value);
              } else if (columnIndex == 6) {
                landLotNameAddress.append(value).append(" ");
              } else if (columnIndex == 7) {
                landLotNameAddress.append(value);
              } else { //roadNameAddress 정의
                if (!value.isEmpty()) {
                  landLotNameAddress.append("-").append(value);
                }
                properties.add(Property.builder()
                    .id((long) rowIndex)
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
    propertyRepository.saveAll(properties);
  }

  @Transactional
  @Override
  public void bulkUpload() throws IOException {
    List<Property> properties = new ArrayList<>();
    FileInputStream file = new FileInputStream(NETWORKING_3_FILEPATH);
    XSSFWorkbook workbook = new XSSFWorkbook(file);
    try {
      // 엑셀파일에서 첫번째 시트 불러오기
      Sheet worksheet = workbook.getSheetAt(0);
      // getPhysicalNumberOfRow 는 행의 갯수를 불러오는 매소드
      for (int rowIndex = 1; rowIndex < worksheet.getPhysicalNumberOfRows(); rowIndex++) {
        // i번째 행 정보 가져오기
        Row row = worksheet.getRow(rowIndex);

        String zipCode = "";
        StringBuilder roadNameAddress = new StringBuilder();
        StringBuilder landLotNameAddress = new StringBuilder();
        if (row != null) {
          for (int columnIndex = 0; columnIndex <= 9; columnIndex++) {
            // i번째 행의 j번째 셀 정보 가져오기
            Cell cell = row.getCell(columnIndex);
            String value = formatCellData(cell);
            if (value != null) {
              if (columnIndex == 0) {
                zipCode = value;
              } else if (columnIndex == 1 || columnIndex == 2) {
                landLotNameAddress.append(value).append(" ");
                roadNameAddress.append(value).append(" ");
              } else if (columnIndex == 3) {
                roadNameAddress.append(value).append(" ");
              } else if (columnIndex == 4) {
                roadNameAddress.append(value);
              } else if (columnIndex == 5) {
                roadNameAddress.append("-").append(value);
              } else if (columnIndex == 6) {
                landLotNameAddress.append(value).append(" ");
              } else if (columnIndex == 7) {
                landLotNameAddress.append(value);
              } else { //roadNameAddress 정의
                if (!value.isEmpty()) {
                  landLotNameAddress.append("-").append(value);
                }
                properties.add(Property.builder()
                    .id((long) rowIndex)
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
    propertyJdbcRepository.saveAll(properties);
  }

//  @Transactional
//  @Override
//  public void jpaDeleteAll() throws IOException {
//    propertyRepository.deleteAll();
//  }

//  @Transactional
//  @Override
//  public void bulkDeleteAll() throws IOException {
//    propertyJdbcRepository.deleteAll();
//  }


  //셀 데이터 형식 포맷
  private static String formatCellData(Cell cell) {
    String value = "";
    // 타입별로 내용 읽기
    if (cell == null) {
      value = null;
    } else {
      switch (cell.getCellType()) {
        case FORMULA:
          value = cell.getCellFormula();
          break;
        case NUMERIC:
          double numericCellValue = cell.getNumericCellValue();
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

