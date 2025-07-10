package com.nike.productservice.dto.mapper;

import com.nike.productservice.dto.ProductDTO;
import com.nike.productservice.entity.ShoeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoeMapper {
    List<ProductDTO> toDTOList(List<ShoeEntity> entity);
}
