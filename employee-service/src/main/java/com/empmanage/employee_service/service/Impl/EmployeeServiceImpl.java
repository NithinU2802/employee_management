package com.empmanage.employee_service.service.Impl;

import com.empmanage.employee_service.dto.EmployeeRequestDTO;
import com.empmanage.employee_service.dto.EmployeeResponseDTO;
import com.empmanage.employee_service.exception.EmailAlreadyExistsException;
import com.empmanage.employee_service.mapper.EmployeeMapper;
import com.empmanage.employee_service.model.Employee;
import com.empmanage.employee_service.repository.EmployeeRepository;
import com.empmanage.employee_service.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponseDTO> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();

        return employees
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO){
        if(employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Employee email "+employeeRequestDTO.getEmail()+" is already exist");
        }
        Employee employee = employeeRepository.save(EmployeeMapper.toEntity(employeeRequestDTO));
        return EmployeeMapper.toDTO(employee);
    }

}
