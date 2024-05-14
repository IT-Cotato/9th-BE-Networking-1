const { Worker } = require('worker_threads');
const XLSX = require('xlsx');
const os = require('os');

const readAndSaveExcelData = async () => {
    console.time("Execution Time"); // 시간 측정 시작

    // 엑셀 파일 읽기
    const workbook = XLSX.readFile('./test_data.xlsx');
    const sheet_name_list = workbook.SheetNames;
    const data = XLSX.utils.sheet_to_json(workbook.Sheets[sheet_name_list[0]]);

    // 데이터를 코어 수에 맞게 분할
    const cpuCount = os.cpus().length;
    const chunkSize = Math.ceil(data.length / cpuCount);
    const chunks = Array.from({length: cpuCount}, (v, i) => data.slice(i * chunkSize, i * chunkSize + chunkSize));

    // 워커 스레드 생성 및 데이터 전송
    const workers = chunks.map(chunk => {
        const worker = new Worker('./models/worker.js');
        worker.postMessage(chunk);
        return worker;
    });

    // 워커 스레드로부터 메시지 받기
    const promises = workers.map(worker => new Promise((resolve) => {
        worker.on('message', (message) => {
            if (message === 'done') {
                resolve();
            } else {
                console.log(message);
            }
        });
    }));

    // 모든 워커 스레드가 작업을 완료할 때까지 기다리기
    await Promise.all(promises);

    console.timeEnd("Execution Time"); // 시간 측정 종료
};

module.exports = { readAndSaveExcelData };