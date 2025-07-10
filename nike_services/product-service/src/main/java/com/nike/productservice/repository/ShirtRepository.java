package com.nike.productservice.repository;

import com.nike.productservice.entity.ShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtRepository extends JpaRepository<ShirtEntity, Long> {
}
