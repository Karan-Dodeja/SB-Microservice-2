package com.employeeservice.service;

import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.entity.Employee;
import com.employeeservice.respository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employeeNew = new Employee(
          employeeDto.getId(),
          employeeDto.getFirstName(),
          employeeDto.getLastName(),
          employeeDto.getEmail()
        );

        Employee savedEmployee = employeeRepository.save(employeeNew);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getSingleEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );

        return employeeDto;
    }

}
