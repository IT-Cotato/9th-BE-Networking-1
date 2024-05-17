package com.cotato.networking1.dto;

import java.util.List;

public record PropertyDeleteListResponse(
        int deletedSize,
        List<Long> deletedIds
) {

    public static PropertyDeleteListResponse from(List<Long> ids) {
        return new PropertyDeleteListResponse(ids.size(), ids);
    }}
