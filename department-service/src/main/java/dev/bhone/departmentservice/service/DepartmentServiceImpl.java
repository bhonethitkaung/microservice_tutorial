package dev.bhone.departmentservice.service;

import dev.bhone.departmentservice.dto.DepartmentDto;
import dev.bhone.departmentservice.dto.mapper.DepartmentMapper;
import dev.bhone.departmentservice.entity.DepartmentEntity;
import dev.bhone.departmentservice.exception.DepartmentNameAlreadyExistsException;
import dev.bhone.departmentservice.exception.DepartmentNotFoundException;
import dev.bhone.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Optional<DepartmentEntity> foundDepartment = repository.findByDepartmentName(departmentDto.getDepartmentName());

        if(foundDepartment.isPresent()) {
            throw new DepartmentNameAlreadyExistsException(departmentDto.getDepartmentName());
        }



        DepartmentEntity entity = DepartmentMapper.mapToEntity(departmentDto);

        DepartmentEntity savedDepartment = repository.save(entity);

        return DepartmentMapper.mapToDto(savedDepartment);
    }

    @Override
    public DepartmentDto getByDepartmentCode(String departmentCode) {
        DepartmentEntity entity = repository.findByDepartmentCode(departmentCode).orElseThrow(() -> new DepartmentNotFoundException(DepartmentEntity.class.getName(), "departmentCode", departmentCode));
        return DepartmentMapper.mapToDto(entity);

    }
}
