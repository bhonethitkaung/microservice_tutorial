package dev.bhone.departmentservice.service;

import dev.bhone.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getByDepartmentCode(String departmentCode);
}
