package com.nike.productservice.controller;


import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.response.BaseResponse;
import com.nike.productservice.dto.response.PageResponse;
import com.nike.productservice.service.ProductService;
import com.nike.productservice.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final UploadFileService uploadFileService;

    @PostMapping(value = "/image")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.uploadFile(file);
    }

    @PostMapping(value = "/{productType}/add")
    public BaseResponse<ProductDTO> createProduct(
            @PathVariable String productType,
            @RequestBody ProductDTO dto) throws IOException {
        try{
            productService.createProduct(productType, dto);
            return BaseResponse.<ProductDTO>builder()
                    .message("Create new product successfully")
                    .data(dto)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<ProductDTO>builder()
                    .message("Error when creating new product!")
                    .build();
        }
    }

    @PostMapping(value = "/upload")
    public String uploadFile(@RequestBody ProductDTO dto) throws IOException {
        return productService.addProduct(dto);
    }

    @PostMapping(value = "/{productType}/page")
    public PageResponse<ProductDTO> getProductPage(@PathVariable String productType,@RequestBody ProductDTO dto) throws IOException {
        return productService.showProductList(productType, dto);
    }
}
