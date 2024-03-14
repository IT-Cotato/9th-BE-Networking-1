package com.cotato.networking1.controller;

import com.cotato.networking1.entity.SampleEntity;
import com.cotato.networking1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class SampleController {
    private final SampleService sampleService;
    @GetMapping("/sample/{id}")
    public ResponseEntity<SampleEntity> sampleAPI(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sampleService.getById(id));
    }
}
