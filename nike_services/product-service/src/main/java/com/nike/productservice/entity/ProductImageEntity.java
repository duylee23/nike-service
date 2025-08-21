package com.nike.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductImageEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private String url;

    private String altText;

    private Integer position;

    private Boolean isPrimary;
}
