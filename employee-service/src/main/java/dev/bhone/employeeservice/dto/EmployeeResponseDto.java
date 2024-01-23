package dev.bhone.employeeservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;
    private OrganisationDto organisation;
}
