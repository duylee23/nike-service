package com.nike.productservice.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private String image;
    private Long categoryId;
    private String status;
    private Integer starCount;
    private Integer pageIndex;
    private Integer pageSize;
}
