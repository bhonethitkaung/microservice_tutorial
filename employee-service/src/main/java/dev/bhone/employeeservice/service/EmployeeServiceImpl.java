package dev.bhone.employeeservice.service;

import dev.bhone.employeeservice.config.ApiClient;
import dev.bhone.employeeservice.dto.DepartmentDto;
import dev.bhone.employeeservice.dto.EmployeeDto;
import dev.bhone.employeeservice.dto.EmployeeResponseDto;
import dev.bhone.employeeservice.dto.OrganisationDto;
import dev.bhone.employeeservice.dto.mapper.EmployeeMapper;
import dev.bhone.employeeservice.entity.EmployeeEntity;
import dev.bhone.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;
//    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private final ApiClient apiClient;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, WebClient webClient, ApiClient apiClient) {
        this.repository = repository;
        this.webClient = webClient;
        this.apiClient = apiClient;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        EmployeeEntity savedEntity = repository.save(EmployeeMapper.mapToEntity(dto));
        return EmployeeMapper.mapToDto(savedEntity);
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public EmployeeResponseDto getById(Long id) {

        LOGGER.info("Inside getById method");
        EmployeeEntity employee = repository.findById(id).get();

//        ResponseEntity<DepartmentDto> departmentResponse = restTemplate.getForEntity(
//                            "http://localhost:8080/departments/" + employee.getDepartmentCode(),
//                            DepartmentDto.class
//        );
//        DepartmentDto departmentDto = departmentResponse.getBody();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        OrganisationDto organisationDto = webClient.get()
                .uri("http://localhost:8083/organisations/" + employee.getOrganisationCode())
                .retrieve()
                .bodyToMono(OrganisationDto.class)
                .block();

        EmployeeDto employeeDto = EmployeeMapper.mapToDto(employee);

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .employee(employeeDto)
                .department(departmentDto)
                .organisation(organisationDto)
                .build();

        return employeeResponseDto;
    }


    public EmployeeResponseDto getDefaultDepartment(Long id, Exception exception) {
        LOGGER.info("Inside getDefaultDepartment method");
        EmployeeEntity employee = repository.findById(id).get();

        DepartmentDto departmentDto = DepartmentDto.builder()
                .departmentName("R&D Department")
                .departmentCode("RD001")
                .departmentDescription("Research and development department")
                .build();

        EmployeeDto employeeDto = EmployeeMapper.mapToDto(employee);

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .employee(employeeDto)
                .department(departmentDto)
                .build();

        return employeeResponseDto;
    }
}
