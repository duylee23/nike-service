package com.nike.productservice.stategy;

import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.response.PageResponse;
import com.nike.productservice.entity.ShirtEntity;
import com.nike.productservice.repository.ShirtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShirtStrategy implements ProductStrategy {
    private final ShirtRepository shirtRepository;
    @Override
    public void createProduct(ProductDTO dto) {
        ShirtEntity shirt = ShirtEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .image(dto.getImage())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
        this.shirtRepository.save(shirt);
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return null;
    }

    @Override
    public PageResponse<ProductDTO> showProductList(ProductDTO dto) {
        return null;
    }
}
