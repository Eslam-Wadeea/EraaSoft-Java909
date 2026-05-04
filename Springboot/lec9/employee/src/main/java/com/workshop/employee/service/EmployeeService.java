package com.workshop.employee.service;

import com.workshop.employee.DTO.EmployeeDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(@Valid EmployeeDTO employee);

    EmployeeDTO updateEmployee(Long id, @Valid EmployeeDTO details);

    void deleteEmployee(Long id);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getById(Long id);

    List<EmployeeDTO> getByIds(List<Long> ids);

    List<EmployeeDTO> getByNames(List<String> names);
}
