const { parentPort } = require('worker_threads');
const { Property } = require('./index');

parentPort.on('message', async (data) => {
    // 데이터 처리
    const processedData = data.map(row => ({
        postalCode: row['우편번호'],
        roadAddress: `${row['시도']} ${row['시군구']} ${row['도로명']} ${row['건물번호 본번']}-${row['건물번호 부번']}`,
        jibunAddress: `${row['시도']} ${row['시군구']} ${row['법정동명']} ${row['지번본번']}-${row['지번부번']}`
    }));

    // 데이터베이스에 데이터 한 번에 저장
    await Property.bulkCreate(processedData);

    // 데이터 처리 작업 완료 후
    parentPort.postMessage('done');
});