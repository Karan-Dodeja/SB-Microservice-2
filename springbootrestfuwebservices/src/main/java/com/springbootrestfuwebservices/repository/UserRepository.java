package com.springbootrestfuwebservices.repository;

import com.springbootrestfuwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}