package com.cotato.networking1.converter;

import com.cotato.networking1.dto.TestDataResponse;

public class TestDataConverter {
    public static TestDataResponse.TestDataDTO toTestDataDTO() {
        return TestDataResponse.TestDataDTO.builder().testDataString("ㅜㅜ").build();
    }
}
