package com.cotato.networking1.enity;


import com.cotato.networking1.model.HouseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HouseDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipCode;

    private String roadNameAddress;

    private String landLotNameAddress;

    public void updateHouse(HouseDto houseDto){

        if (houseDto.getZipCode() != null){
            this.zipCode = houseDto.getZipCode();
        }
        if(houseDto.getRoadNameAddress() != null){
            this.roadNameAddress = houseDto.getRoadNameAddress();
        }
        if(houseDto.getLandLotNameAddress() != null){
            this.landLotNameAddress = houseDto.getLandLotNameAddress();
        }


    }

}
