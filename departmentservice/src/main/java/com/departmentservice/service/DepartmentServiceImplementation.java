package com.departmentservice.service;

import com.departmentservice.dto.DepartmentDto;
import com.departmentservice.entity.Department;
import com.departmentservice.mapper.DepartmentMapper;
import com.departmentservice.repository.Departmentrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImplementation implements DEpartmentService {

    private Departmentrepository departmentrepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        // COnvert DepartmentDto to DepartmentJpa Entity
        Department savedDepartment = departmentrepository.save(department);

        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentrepository.findByDepartmentCode(code);

        DepartmentDto newDepartmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return newDepartmentDto;
    }
}
