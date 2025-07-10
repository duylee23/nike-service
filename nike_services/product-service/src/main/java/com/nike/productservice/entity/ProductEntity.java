package com.nike.productservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "product")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductEntity extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private String image;
    private Long categoryId;
    private String status;
    private Integer starCount;
}
