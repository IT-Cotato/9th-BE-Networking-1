package com.cotato.networking1.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyListResponseDTO<T> {
    private T properties;
}
