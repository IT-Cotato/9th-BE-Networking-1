package com.cotato.networking1.common.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cotato.networking1.common.dto.DataResponse;
import com.cotato.networking1.estate.service.EstateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelManageServiceImpl {
	private final int ZIP_CODE = 0;
	private final int SI_OR_DO = 1;
	private final int SI_GUN_GU = 2;
	private final int ROAD_NAME_ADDRESS = 3;
	private final int BUILDING_MAIN_NUMBER = 4;
	private final int BUILDING_SUB_NUMBER = 5;
	private final int DONG = 6;
	private final int MAIN_STREET_NUMBER = 7;
	private final int SUB_STREET_NUMBER = 8;

	private final String FILE_NAME = "C:/Users/hl347/Desktop/코테이토/backendNetworking/서울시_20만개.xlsx";

	private final EstateService estateService;

	public DataResponse upload() {
		long startTime = System.currentTimeMillis();

		try {
			List<List<String>> dataList = parseEstateExcelToList(FILE_NAME);
			// 저장
			saveEstates(dataList);

		} catch (Exception e) {
			return DataResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		long stopTime = System.currentTimeMillis();
		log.info("시작시간 : {}, 종료시간 : {} ,소요시간 : {}", startTime, stopTime, stopTime - startTime);
		return DataResponse.ok();
	}

	private List<List<String>> parseEstateExcelToList(String filePath) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		List<List<String>> results = new ArrayList<>();

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

					if (value == null && columnindex != BUILDING_SUB_NUMBER) {
						throw new RuntimeException("엑셀의 형식이 잘 못 되었습니다.");
					}

					//우편번호 만들기
					if (columnindex == ZIP_CODE) {
						zipCode = value;
					}

					//도로명 받기 만들기
					if (columnindex == SI_OR_DO || columnindex == SI_GUN_GU || columnindex == ROAD_NAME_ADDRESS) {
						roadNameAddress.append(value).append(" ");
					} else if (columnindex == BUILDING_MAIN_NUMBER) {
						roadNameAddress.append(value).append("-");
					} else if (columnindex == BUILDING_SUB_NUMBER) {
						if (value == null) {
							continue;
						}
						roadNameAddress.append(value);
					}

					//지번주소 만들기
					if (columnindex == SI_OR_DO || columnindex == SI_GUN_GU || columnindex == DONG) {
						landLotNameAddress.append(value).append(" ");
					} else if (columnindex == MAIN_STREET_NUMBER) {
						landLotNameAddress.append(value).append("-");
					} else if (columnindex == SUB_STREET_NUMBER) {
						landLotNameAddress.append(value);
					}
				}
			}
			List<String> result_item = new ArrayList<>();
			result_item.add(zipCode);
			result_item.add(roadNameAddress.toString());
			result_item.add(landLotNameAddress.toString());

			results.add(result_item);
		}

		return results;
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

	//엑셀을 db에 저장
	public void saveEstates(List<List<String>> dataList) {
		estateService.saveAllEstateByBulk(dataList);
	}
}