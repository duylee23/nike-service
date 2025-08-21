package com.nike.productservice.repository;

import com.nike.productservice.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
