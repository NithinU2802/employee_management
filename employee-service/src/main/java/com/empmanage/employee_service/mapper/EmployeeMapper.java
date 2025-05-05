package com.empmanage.employee_service.mapper;

import com.empmanage.employee_service.dto.EmployeeRequestDTO;
import com.empmanage.employee_service.dto.EmployeeResponseDTO;
import com.empmanage.employee_service.model.Employee;

import java.time.LocalDate;

public class EmployeeMapper {
    public static EmployeeResponseDTO toDTO(Employee employee){
        EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();
        employeeDTO.setId(employee.getId().toString());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDataOfBirith(employee.getDateOfBirth().toString());
        return employeeDTO;
    }

    public static Employee toEntity(EmployeeRequestDTO employeeRequestDTO){
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setDateOfBirth(LocalDate.parse(employeeRequestDTO.getDateOfBirth()));
        employee.setJoinDate(LocalDate.parse(employeeRequestDTO.getJoinDate()));
        return employee;
    }

}
