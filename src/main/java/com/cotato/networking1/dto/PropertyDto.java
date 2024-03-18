package com.cotato.networking1.dto;

import com.cotato.networking1.entity.Property;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO for {@link com.cotato.networking1.entity.Property}
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDto implements Serializable {

  Long id;
  Integer zipCode;
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