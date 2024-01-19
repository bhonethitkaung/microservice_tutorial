package dev.bhone.departmentservice.dto.mapper;

import dev.bhone.departmentservice.dto.DepartmentDto;
import dev.bhone.departmentservice.entity.DepartmentEntity;

public class DepartmentMapper {

    public static DepartmentDto mapToDto(DepartmentEntity entity) {
        return DepartmentDto.builder()
                .id(entity.getId())
                .departmentName(entity.getDepartmentName())
                .departmentCode(entity.getDepartmentCode())
                .departmentDescription(entity.getDepartmentDescription())
                .build();
    }

    public static DepartmentEntity mapToEntity(DepartmentDto dto) {
        return DepartmentEntity.builder()
                .id(dto.getId())
                .departmentName(dto.getDepartmentName())
                .departmentCode(dto.getDepartmentCode())
                .departmentDescription(dto.getDepartmentDescription())
                .build();
    }
}
