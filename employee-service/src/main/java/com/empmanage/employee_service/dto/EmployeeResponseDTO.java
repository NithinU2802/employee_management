package com.empmanage.employee_service.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dataOfBirith;
}
