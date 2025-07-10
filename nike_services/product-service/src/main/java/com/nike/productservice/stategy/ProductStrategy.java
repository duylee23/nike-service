package com.nike.productservice.stategy;


import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.response.PageResponse;

public interface ProductStrategy {
    void createProduct(ProductDTO dto);
    ProductDTO getProduct(Long id);
    PageResponse<ProductDTO> showProductList(ProductDTO dto);
}
