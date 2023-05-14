package com.departmentservice.service;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.entity.Department;
import com.departmentservice.repository.Departmentrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImplementation implements DEpartmentService {

    private Departmentrepository departmentrepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // COnvert DepartmentDto to DepartmentJpa Entity
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
                );

        Department savedDepartment = departmentrepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
           savedDepartment.getId(),
                savedDepartment.getDepartmentCode(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription()
        ) ;

        return savedDepartmentDto;
    }
}
