package com.nike.productservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "shirt")
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class ShirtEntity extends ProductEntity  {
}
