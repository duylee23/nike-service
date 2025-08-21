package com.nike.productservice.controller;

import com.nike.productservice.dto.request.CategoryReq;
import com.nike.productservice.dto.response.BaseResponse;
import com.nike.productservice.dto.response.CategoryRes;
import com.nike.productservice.entity.CategoryEntity;
import com.nike.productservice.repository.CategoryRepository;
import com.nike.productservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public BaseResponse<CategoryRes> create(@Valid @RequestBody CategoryReq request) {
        CategoryRes saved = this.categoryService.save(request);
        return BaseResponse.<CategoryRes>builder()
                .success(true)
                .message("Category created")
                .data(saved)
                .build();
    }
}
