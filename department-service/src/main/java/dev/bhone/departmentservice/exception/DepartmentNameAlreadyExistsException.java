package dev.bhone.departmentservice.exception;

public class DepartmentNameAlreadyExistsException extends  RuntimeException {
    private String fieldValue;

    public DepartmentNameAlreadyExistsException(String fieldValue) {
        super(String.format("Department name %s already exists", fieldValue));
    }
}
