package com.cotato.networking1.domain.dto;

import com.cotato.networking1.domain.enttiy.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PropertyListResponse {
    List<Property> properties;
}
