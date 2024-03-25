package domain.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "real_estate")
public class Property{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zipCode", unique = true)
    private String zipCode;

    @Column(name = "roadNameAddress")
    private String roadNameAddress;

    @Column(name = "landRoadNameAddress")
    private String landRoadNameAddress;

    @Builder
    public Property(Long id, String zipCode, String roadNameAddress, String landRoadNameAddress){
        this.id=id;
        this.zipCode=zipCode;
        this.roadNameAddress= roadNameAddress;
        this.landRoadNameAddress=landRoadNameAddress;
    }



}









