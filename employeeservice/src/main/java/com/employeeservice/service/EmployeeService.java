package com.employeeservice.service;

import com.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getSingleEmployee(Long employeeId);

}
