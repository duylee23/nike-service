package com.nike.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_variant")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductVariantEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false, unique = true)
    private String sku;

    private String color;

    private String size;

    private Double price;

    private Integer stockQuantity;

    private String imageUrl;
}
