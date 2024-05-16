package com.cotato.networking1.dto;

import com.cotato.networking1.entity.Property;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for {@link com.cotato.networking1.entity.Property}
 */


@Data
@AllArgsConstructor
@Builder
public class PropertyDto implements Serializable {

  Long id;
  String zipCode;
  String roadNameAddress;
  String landLotNameAddress;

  public Property toEntity() {
    return Property.builder()
        .id(id)
        .zipCode(zipCode)
        .roadNameAddress(roadNameAddress)
        .landLotNameAddress(landLotNameAddress).build();
  }

}