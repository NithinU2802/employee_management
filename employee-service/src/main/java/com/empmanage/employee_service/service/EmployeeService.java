package com.empmanage.employee_service.service;

import com.empmanage.employee_service.dto.EmployeeRequestDTO;
import com.empmanage.employee_service.dto.EmployeeResponseDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    public List<EmployeeResponseDTO> getEmployees();
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO employeeRequestDTO);
    public void deleteEmployee(UUID id);
}
