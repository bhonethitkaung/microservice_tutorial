package dev.bhone.employeeservice.service;

import dev.bhone.employeeservice.dto.EmployeeDto;
import dev.bhone.employeeservice.dto.EmployeeResponseDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto dto);

    EmployeeResponseDto getById(Long id);

}
