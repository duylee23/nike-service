package com.nike.productservice.repository;

import com.nike.productservice.entity.ShoeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeEntity, Long> {

}
