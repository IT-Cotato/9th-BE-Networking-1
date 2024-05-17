package com.cotato.networking1.dto.response;

import java.util.List;

public record FindPropertyResponse (
        List<PropertyResponse> properties
){
    public static FindPropertyResponse from(List<PropertyResponse> properties){
        return new FindPropertyResponse(properties);
    }
}
