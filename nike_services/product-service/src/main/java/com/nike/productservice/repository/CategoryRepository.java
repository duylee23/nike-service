package com.nike.productservice.repository;

import com.nike.productservice.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findCategoryEntityByName(String name);
    boolean existsByName(String name);
}
