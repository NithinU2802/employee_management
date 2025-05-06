package com.empmanage.employee_service.service.Impl;

import com.empmanage.employee_service.dto.EmployeeRequestDTO;
import com.empmanage.employee_service.dto.EmployeeResponseDTO;
import com.empmanage.employee_service.exception.EmailAlreadyExistsException;
import com.empmanage.employee_service.exception.EmployeeNotFoundException;
import com.empmanage.employee_service.mapper.EmployeeMapper;
import com.empmanage.employee_service.model.Employee;
import com.empmanage.employee_service.repository.EmployeeRepository;
import com.empmanage.employee_service.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public EmployeeResponseDTO updateEmployee(UUID id,EmployeeRequestDTO employeeRequestDTO){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Patient not found with ID: "+id));
        if(employeeRepository.existsByEmailAndIdNot(employeeRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException("Employee email "+employeeRequestDTO.getEmail()+" is already exist");
        }
        employee.setName(employeeRequestDTO.getName());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setDateOfBirth(LocalDate.parse(employeeRequestDTO.getDateOfBirth()));
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(updatedEmployee);
    }

    public void deleteEmployee(UUID id){
        employeeRepository.deleteById(id);
    }

}
