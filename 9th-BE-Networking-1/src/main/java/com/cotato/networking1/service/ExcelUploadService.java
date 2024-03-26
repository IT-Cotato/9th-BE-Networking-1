package com.cotato.networking1.service;

import com.cotato.networking1.domain.Property;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    // 업로드된 파일이 유효한 엑셀 파일인지 확인
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    // 엑셀 파일에서 속성 데이터 읽어오기
    public static List<Property> getPropertiesDataFromExcel(InputStream inputStream){
        List<Property> properties = new ArrayList<>();

        XSSFWorkbook workbook = null;

        try {
            // 엑셀파일 읽어오기
            workbook = new XSSFWorkbook(inputStream);
            // sheet 이름
            XSSFSheet sheet = workbook.getSheet("Sheet1");


            int rowIndex = 0;
            for (Row row : sheet){
                // 헤더행 처리
                if (rowIndex == 0){
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                Property.PropertyBuilder propertyBuilder = Property.builder();

                StringBuilder roadAddressBuilder = new StringBuilder();
                StringBuilder lotNumberAddressBuilder = new StringBuilder();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()){
                        case 0:
                            propertyBuilder.zipCode(cellToString(cell));
                            break;
                        case 1:
                            propertyBuilder.roadAddress(cellToString(cell));
                            roadAddressBuilder.append(cellToString(cell));
                            propertyBuilder.lotNumberAddress(cellToString(cell));
                            lotNumberAddressBuilder.append(cellToString(cell));
                            break;
                        case 2:
                            roadAddressBuilder.append(" ").append(cellToString(cell));
                            lotNumberAddressBuilder.append(" ").append(cellToString(cell));
                            break;
                        case 3:
                            roadAddressBuilder.append(" ").append(cellToString(cell));
                            break;
                        case 4:
                            roadAddressBuilder.append(" ").append(cellToString(cell));
                            break;
                        case 5:
                            roadAddressBuilder.append(cellToString(cell));
                            break;
                        case 6:
                            lotNumberAddressBuilder.append(" ").append(cellToString(cell));
                            break;
                        case 7:
                            lotNumberAddressBuilder.append(" ").append(cellToString(cell));
                            break;
                        case 8:
                            lotNumberAddressBuilder.append("-").append(cellToString(cell));
                            break;
                        default:
                            break;
                    }
                }

                Property property = propertyBuilder
                        .lotNumberAddress(lotNumberAddressBuilder.toString())
                        .roadAddress(roadAddressBuilder.toString())
                        .build();
                properties.add(property);


            }
        } catch (IOException e){
            e.getStackTrace();
        }

        return properties;
    }

    // 다른 형식의 셀 값을 문자열로 변환
    private static String cellToString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue()).replace(".0", "");
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }


}
