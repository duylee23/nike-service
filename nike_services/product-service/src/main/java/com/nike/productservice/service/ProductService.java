package com.nike.productservice.service;

import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.response.PageResponse;


public interface ProductService {
//    PageResponse<ProductDTO> getPage(ProductDTO dto) throws IOException;

    String addProduct(ProductDTO dto);

    void createProduct (String productType, ProductDTO dto);

    PageResponse<ProductDTO> showProductList(String productType, ProductDTO dto);
}
