package com.cotato.networking1.common.excel;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.service.EstateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelManageServiceImpl {

	private final String filePath = "C:/Users/hl347/Desktop/코테이토/backendNetworking/매물_정보.xlsx";

	private final EstateService estateService;

	public DataResponse upload() {
		try {
			FileInputStream file = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			int rowindex = 0;
			int columnindex = 0;
			//시트 수 (첫번째에만 존재하므로 0을 준다)
			//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
			XSSFSheet sheet = workbook.getSheetAt(0);

			//행의 수
			int rows = sheet.getPhysicalNumberOfRows();

			for (rowindex = 1; rowindex <= rows; rowindex++) {
				XSSFRow row = sheet.getRow(rowindex);

				String zipCode = "";
				StringBuilder roadNameAddress = new StringBuilder();
				StringBuilder landLotNameAddress = new StringBuilder();
				if (row != null) {
					//셀의 수
					int cellNumber = row.getPhysicalNumberOfCells();

					for (columnindex = 0; columnindex < cellNumber; columnindex++) {
						//셀값을 읽는다
						XSSFCell cell = row.getCell(columnindex);
						String value = readCellByType(cell);

						if (value == null && columnindex != 5) {
							return DataResponse.error(HttpStatus.BAD_REQUEST.value());
						}

						//우편번호 만들기
						if (columnindex == 0) {
							zipCode = value;
						}

						//도로명 받기 만들기
						if (columnindex == 1 || columnindex == 2 || columnindex == 3) {
							roadNameAddress.append(value).append(" ");
						} else if (columnindex == 4) {
							roadNameAddress.append(value).append("-");
						} else if (columnindex == 5) {
							if (value == null) {
								continue;
							}
							roadNameAddress.append(value);
						}

						//지번주소 만들기
						if (columnindex == 1 || columnindex == 2 || columnindex == 6) {
							landLotNameAddress.append(value).append(" ");
						} else if (columnindex == 7) {
							landLotNameAddress.append(value).append("-");
						} else if (columnindex == 8) {
							landLotNameAddress.append(value);
						}
					}
				}
				estateService.saveEstate(zipCode, roadNameAddress.toString(), landLotNameAddress.toString());
			}

		} catch (Exception e) {
			return DataResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return DataResponse.ok();
	}

	//타입별로 내용 읽기
	private String readCellByType(XSSFCell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
				case FORMULA:
					return cell.getCellFormula();
				case NUMERIC:
					return String.valueOf((int)cell.getNumericCellValue());
				case STRING:
					return cell.getStringCellValue();
				case BLANK:
					return cell.getBooleanCellValue() + "";
				case ERROR:
					return cell.getErrorCellValue() + "";
			}
		}
		return null;
	}

}