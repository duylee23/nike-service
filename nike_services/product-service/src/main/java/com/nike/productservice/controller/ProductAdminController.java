package com.nike.productservice.controller;

import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.response.BaseResponse;
import com.nike.productservice.entity.*;
import com.nike.productservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductRepository productRepository;
    private final ProductVariantRepository variantRepository;
    private final ProductImageRepository imageRepository;
    private final ReviewRepository reviewRepository;

    @PostMapping
    public BaseResponse<ProductEntity> createBase(@RequestBody ProductEntity request) {
        ProductEntity saved = productRepository.save(request);
        return BaseResponse.<ProductEntity>builder()
                .success(true)
                .message("Product created")
                .data(saved)
                .build();
    }

    @PostMapping("/{productId}/variants")
    public BaseResponse<ProductVariantEntity> createVariant(@PathVariable Long productId,
                                                            @RequestBody ProductVariantEntity request) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        request.setProduct(product);
        ProductVariantEntity saved = variantRepository.save(request);
        return BaseResponse.<ProductVariantEntity>builder()
                .success(true)
                .message("Variant created")
                .data(saved)
                .build();
    }

    @PostMapping("/{productId}/images")
    public BaseResponse<ProductImageEntity> createImage(@PathVariable Long productId,
                                                        @RequestBody ProductImageEntity request) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        request.setProduct(product);
        ProductImageEntity saved = imageRepository.save(request);
        return BaseResponse.<ProductImageEntity>builder()
                .success(true)
                .message("Image created")
                .data(saved)
                .build();
    }

    @PostMapping("/{productId}/reviews")
    public BaseResponse<ReviewEntity> createReview(@PathVariable Long productId,
                                                   @RequestBody ReviewEntity request) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        request.setProduct(product);
        ReviewEntity saved = reviewRepository.save(request);
        return BaseResponse.<ReviewEntity>builder()
                .success(true)
                .message("Review created")
                .data(saved)
                .build();
    }
}
