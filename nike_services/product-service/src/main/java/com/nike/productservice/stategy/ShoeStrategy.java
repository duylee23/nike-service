package com.nike.productservice.stategy;

import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.dto.mapper.ShoeMapper;
import com.nike.productservice.dto.response.PageResponse;
import com.nike.productservice.entity.ShoeEntity;
import com.nike.productservice.repository.ShoeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ShoeStrategy implements ProductStrategy {
    private final ShoeRepository shoeRepository;
    private final ShoeMapper shoeMapper;
    @Override
    public void createProduct(ProductDTO dto) {
        ShoeEntity shoe = ShoeEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .image(dto.getImage())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
        this.shoeRepository.save(shoe);
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return null;
    }

    @Override
    public PageResponse<ProductDTO> showProductList(ProductDTO dto) {
        Pageable pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize());
        Page<ShoeEntity> page = this.shoeRepository.findAll(pageable);
        List<ProductDTO> dtoList = this.shoeMapper.toDTOList(page.getContent());
        return PageResponse.<ProductDTO>builder()
                .items(dtoList)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize())
                .totalElements((int) page.getTotalElements())
                .build();
    }
}
