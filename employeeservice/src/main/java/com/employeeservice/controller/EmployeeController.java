package com.employeeservice.controller;

import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor

public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getSingleEmployee(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

}