package dev.bhone.employeeservice.service;

import dev.bhone.employeeservice.config.ApiClient;
import dev.bhone.employeeservice.dto.DepartmentDto;
import dev.bhone.employeeservice.dto.EmployeeDto;
import dev.bhone.employeeservice.dto.EmployeeResponseDto;
import dev.bhone.employeeservice.dto.mapper.EmployeeMapper;
import dev.bhone.employeeservice.entity.EmployeeEntity;
import dev.bhone.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository repository;
//    private final RestTemplate restTemplate;
//    private final WebClient webClient;
    private final ApiClient apiClient;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, ApiClient apiClient) {
        this.repository = repository;
        this.apiClient = apiClient;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        EmployeeEntity savedEntity = repository.save(EmployeeMapper.mapToEntity(dto));
        return EmployeeMapper.mapToDto(savedEntity);
    }

    @Override
    public EmployeeResponseDto getById(Long id) {
        EmployeeEntity employee = repository.findById(id).get();

//        ResponseEntity<DepartmentDto> departmentResponse = restTemplate.getForEntity(
//                            "http://localhost:8080/departments/" + employee.getDepartmentCode(),
//                            DepartmentDto.class
//        );
//        DepartmentDto departmentDto = departmentResponse.getBody();

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = EmployeeMapper.mapToDto(employee);

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .employee(employeeDto)
                .department(departmentDto)
                .build();

        return employeeResponseDto;
    }
}
