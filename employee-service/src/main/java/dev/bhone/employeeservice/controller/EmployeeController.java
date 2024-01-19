package dev.bhone.employeeservice.controller;

import dev.bhone.employeeservice.dto.EmployeeDto;
import dev.bhone.employeeservice.dto.EmployeeResponseDto;
import dev.bhone.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto dto = service.saveEmployee(employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponseDto employeeResponseDto = service.getById(id);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }
}
