package com.nike.productservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;


@Entity
@Getter
@Setter
@ToString(exclude = {"category", "brand", "images", "variants", "reviews"})
@NoArgsConstructor
@Table(name = "product")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductEntity extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private String image;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "brand_id")
    private Long brandId;
    private String status;
    private Integer starCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BrandEntity brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImageEntity> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;
}
