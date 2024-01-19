package dev.bhone.departmentservice.repository;

import dev.bhone.departmentservice.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);

    Optional<DepartmentEntity> findByDepartmentName(String departmentName);
}
