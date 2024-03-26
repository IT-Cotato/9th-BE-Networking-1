@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getPropertiesByZipCode(String zipCode) {
        return propertyRepository.findByZipCode(zipCode);
    }

    public void deletePropertyByRoadNameAddress(String roadNameAddress) {
        propertyRepository.deleteByRoadNameAddress(roadNameAddress);
    }
}