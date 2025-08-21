package com.nike.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_review")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ReviewEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer rating; // 1-5

    private String title;

    @Column(length = 4000)
    private String content;

    private Long userId;

    private String userDisplayName;
}
