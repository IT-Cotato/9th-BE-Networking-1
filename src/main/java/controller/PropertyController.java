@RestController
@RequestMapping("/api")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getPropertiesByZipCode(@RequestParam("zip-code") String zipCode) {
        List<Property> properties = propertyService.getPropertiesByZipCode(zipCode);
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping("/properties")
    public ResponseEntity<String> deletePropertyByRoadNameAddress(@RequestParam("road-name-address") String roadNameAddress) {
        propertyService.deletePropertyByRoadNameAddress(roadNameAddress);
        return ResponseEntity.ok("Property deleted successfully.");
    }
}