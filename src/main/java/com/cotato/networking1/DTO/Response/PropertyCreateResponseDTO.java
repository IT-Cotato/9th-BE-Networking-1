package com.cotato.networking1.DTO.Response;

import lombok.Getter;

@Getter
public class PropertyCreateResponseDTO {
    private final Long id;

    public PropertyCreateResponseDTO(Long id) {
        this.id = id;
    }

    public static PropertyCreateResponseDTO register(Long id){
        return new PropertyCreateResponseDTO(id);
    }
}
