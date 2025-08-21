package com.nike.productservice.service.impl;

import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.response.PageResponse;
import com.nike.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public String addProduct(ProductDTO dto) {
        return "";
    }

    @Override
    public void createProduct(String productType, ProductDTO dto) {

    }

    @Override
    public PageResponse<ProductDTO> showProductList(String productType, ProductDTO dto) {
        return null;
    }
}
