const XLSX = require('xlsx');
const { Property } = require('./index');

const readAndSaveExcelData = async () => {
    // Property 테이블이 비어 있는지 확인
    const count = await Property.count();
    if (count > 0) {
        return;
    } // 이미 데이터가 존재하면 함수 종료
    // 엑셀 파일 읽기
    const workbook = XLSX.readFile('./test_data.xlsx');
    const sheet_name_list = workbook.SheetNames;
    const data = XLSX.utils.sheet_to_json(workbook.Sheets[sheet_name_list[0]]);

    // 데이터베이스에 데이터 저장
    for (const row of data) {
        await Property.create({
            postalCode: row['우편번호'],
            roadAddress: `${row['시도']} ${row['시군구']} ${row['도로명']} ${row['건물번호 본번']}-${row['건물번호 부번']}`,
            jibunAddress: `${row['시도']} ${row['시군구']} ${row['법정동명']} ${row['지번본번']}-${row['지번부번']}`
        });
    }
};

module.exports = { readAndSaveExcelData };