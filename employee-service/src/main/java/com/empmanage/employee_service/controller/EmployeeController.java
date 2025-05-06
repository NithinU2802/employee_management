package com.empmanage.employee_service.controller;

import com.empmanage.employee_service.dto.EmployeeRequestDTO;
import com.empmanage.employee_service.dto.EmployeeResponseDTO;
import com.empmanage.employee_service.dto.Validators.CreateEmployeeValidationGroup;
import com.empmanage.employee_service.service.EmployeeService;
import com.empmanage.employee_service.service.Impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl){
        this.employeeService = employeeServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
            return ResponseEntity.ok().body(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Validated({Default.class, CreateEmployeeValidationGroup.class}) @RequestBody EmployeeRequestDTO employeeRequestDTO){
        return ResponseEntity.ok().body(employeeService.createEmployee(employeeRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable UUID id,@RequestBody @Validated({Default.class}) EmployeeRequestDTO employeeRequestDTO){
        return ResponseEntity.ok().body(employeeService.updateEmployee(id,employeeRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
