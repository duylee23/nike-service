package com.nike.authservice.repository;

import com.nike.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {
}
