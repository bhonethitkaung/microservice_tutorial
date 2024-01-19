package dev.bhone.employeeservice.dto.mapper;

import dev.bhone.employeeservice.dto.EmployeeDto;
import dev.bhone.employeeservice.entity.EmployeeEntity;

public class EmployeeMapper {

    public static EmployeeDto mapToDto(EmployeeEntity employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .departmentCode(employee.getDepartmentCode())
                .build();
    }

    public static EmployeeEntity mapToEntity(EmployeeDto dto) {
        return EmployeeEntity.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .departmentCode(dto.getDepartmentCode())
                .build();
    }
}
