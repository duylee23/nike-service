package com.nike.productservice.controller;

import com.nike.productservice.dto.response.BaseResponse;
import com.nike.productservice.entity.BrandEntity;
import com.nike.productservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandRepository brandRepository;

    @PostMapping
    public BaseResponse<BrandEntity> create(@RequestBody BrandEntity request) {
        BrandEntity saved = brandRepository.save(request);
        return BaseResponse.<BrandEntity>builder()
                .success(true)
                .message("Brand created")
                .data(saved)
                .build();
    }
}
