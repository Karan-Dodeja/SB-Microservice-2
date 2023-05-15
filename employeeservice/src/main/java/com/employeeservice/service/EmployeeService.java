package com.employeeservice.service;

import com.employeeservice.dto.APIResponseDto;
import com.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getSingleEmployee(Long employeeId);

}
