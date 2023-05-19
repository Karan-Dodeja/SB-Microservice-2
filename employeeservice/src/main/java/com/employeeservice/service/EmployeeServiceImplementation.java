package com.employeeservice.service;

import com.employeeservice.dto.APIResponseDto;
import com.employeeservice.dto.DepartmentDto;
import com.employeeservice.dto.EmployeeDto;
import com.employeeservice.dto.OrganizationDto;
import com.employeeservice.entity.Employee;
import com.employeeservice.mapper.EmployeeMapper;
import com.employeeservice.respository.EmployeeRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.namespace.QName;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImplementation.class);

    private EmployeeRepository employeeRepository;


    private WebClient webClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    // @CircuitBreaker()
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getSingleEmployee(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");

        Employee employee = employeeRepository.findById(employeeId).get();

//         ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
   //             DepartmentDto.class
     //           );

       // DepartmentDto departmentDto = responseEntity.getBody();

        // OrganizationDto organizationDto = apiClient.getOrganizationByCode(employee.getOrganizationCode());

        // DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());


        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
           apiResponseDto.setEmployee(employeeDto);
           apiResponseDto.setDepartment(departmentDto);
           apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;

    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
            // apiResponseDto.setEmployee(employeeDto);
            // apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }

}
