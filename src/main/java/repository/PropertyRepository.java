
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByZipCode(String zipCode);

    void deleteByRoadNameAddress(String roadNameAddress);
}
