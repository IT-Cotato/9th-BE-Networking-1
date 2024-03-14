package com.cotato.networking1.service;

import com.cotato.networking1.entity.SampleEntity;
import com.cotato.networking1.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    public SampleEntity getById(Long id) {
        return sampleRepository.findById(id).get();
    }
}
