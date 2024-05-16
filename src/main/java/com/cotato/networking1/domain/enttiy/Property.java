package com.cotato.networking1.domain.enttiy;

import com.cotato.networking1.domain.dto.PropertyRegisterRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@TableGenerator(
        name = "PROPERTY_GENERATOR", //식별자 생성기 이름
        table = "sequence_table", //키 생성 테이블명
        pkColumnName = "sequence_name", //시퀀스 컬럼명
        valueColumnName = "next_val", //시퀀스 값 컬럼명
        initialValue = 1, //초기 값
        allocationSize = 200000
)
public class Property {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
            , generator = "PROPERTY_GENERATOR"
    )
    @Column(name = "property_id")
    private Long id;
    private String zipCode;
    private String roadNameAddress;
    private String landLotNameAddress;

    public static Property of(PropertyRegisterRequest propertyRegisterRequest) {
        return Property.builder()
                .landLotNameAddress(propertyRegisterRequest.landLotNameAddress())
                .zipCode(propertyRegisterRequest.zipCode())
                .roadNameAddress(propertyRegisterRequest.roadNameAddress())
                .build();
    }
}
