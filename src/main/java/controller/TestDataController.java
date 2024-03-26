@RestController
@RequestMapping("/api")
public class TestDataController {

    @Autowired
    private TestDataParsingService testDataParsingService;

    @PostMapping("/test-data")
    public ResponseEntity<String> parseAndSaveTestData(@RequestParam("file") MultipartFile file) {
        try {
            testDataParsingService.parseAndSaveTestData(file);
            return ResponseEntity.ok("Data parsed and saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to parse and save data: " + e.getMessage());
        }
    }
}
