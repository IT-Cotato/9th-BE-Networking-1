@Service
public class TestDataParsingService {

    @Autowired
    private PropertyRepository propertyRepository;

    public void parseAndSaveTestData(MultipartFile file) throws IOException {
        // 엑셀 파싱 로직 구현
        List<Property> properties = ExcelParser.parseExcel(file.getInputStream());
        propertyRepository.saveAll(properties);
    }
}