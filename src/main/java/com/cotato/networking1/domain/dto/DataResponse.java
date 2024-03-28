package com.cotato.networking1.domain.dto;

import lombok.Getter;

@Getter
public class DataResponse <T>{

    private final T properties;

    private DataResponse(T properties) {
        this.properties = properties;
    }

    public static <T> DataResponse<T> ok(T properties) {
        return new DataResponse<>(properties);
    }
}
