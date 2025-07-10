package com.nike.userservice.repository;

import com.nike.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {
}
