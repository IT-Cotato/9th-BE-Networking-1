const XLSX = require('xlsx');
const { Property } = require('./index');

const readAndSaveExcelData = async () => {
    // 엑셀 파일 읽기
    const workbook = XLSX.readFile('./매물_정보.xlsx');
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