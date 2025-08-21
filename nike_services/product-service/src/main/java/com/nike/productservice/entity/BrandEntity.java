package com.nike.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "brand")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private String website;

    private String country;
}
