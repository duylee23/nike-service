package com.nike.productservice.service;

import com.nike.productservice.dto.request.CategoryReq;
import com.nike.productservice.dto.response.CategoryRes;

public interface CategoryService {
    CategoryRes save (CategoryReq req);
}
