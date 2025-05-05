package com.empmanage.employee_service.controller;

import com.empmanage.employee_service.dto.EmployeeRequestDTO;
import com.empmanage.employee_service.dto.EmployeeResponseDTO;
import com.empmanage.employee_service.service.EmployeeService;
import com.empmanage.employee_service.service.Impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        return ResponseEntity.ok().body(employeeService.createEmployee(employeeRequestDTO));
    }

}
