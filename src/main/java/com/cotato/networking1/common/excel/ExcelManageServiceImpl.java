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
				//행을읽는다
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					String zipCode = "";
					StringBuilder roadNameAddress = new StringBuilder();
					StringBuilder landLotNameAddress = new StringBuilder();

					//셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex < cells; columnindex++) {
						//셀값을 읽는다
						XSSFCell cell = row.getCell(columnindex);
						String value = readCellByType(cell);
						log.info("value: " + value);

						if (value == null && columnindex != 5) {
							return DataResponse.error(HttpStatus.BAD_REQUEST.value());
						}

						if (columnindex == 0) {
							zipCode = value;
						}

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

						if (columnindex == 1 || columnindex == 2 || columnindex == 6) {
							landLotNameAddress.append(value).append(" ");
						} else if (columnindex == 7) {
							landLotNameAddress.append(value).append("-");
						} else if (columnindex == 8) {
							landLotNameAddress.append(value);
						}
					}
					log.info("zipCode: " + zipCode);
					log.info("road: " + roadNameAddress.toString());
					log.info("land: " + landLotNameAddress.toString());
					estateService.saveEstate(zipCode, roadNameAddress.toString(), landLotNameAddress.toString());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return DataResponse.ok();
	}

	private String readCellByType(XSSFCell cell) {
		if (cell != null) {
			//타입별로 내용 읽기
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