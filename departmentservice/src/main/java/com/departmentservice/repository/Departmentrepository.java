package com.departmentservice.repository;

import com.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Departmentrepository extends JpaRepository<Department, Long> {
}
