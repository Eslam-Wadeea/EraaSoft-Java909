package com.workshop.employee.service.impl;

import com.workshop.employee.DTO.EmployeeDTO;
import com.workshop.employee.mapper.EntityMapper;
import com.workshop.employee.model.Employee;
import com.workshop.employee.repository.EmployeeRepository;
import com.workshop.employee.service.EmployeeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EntityMapper mapper;
    private final EmployeeRepository employeeRepo;


    @Override
    @Transactional
    public EmployeeDTO createEmployee(@Valid EmployeeDTO dto) {

        Employee employeeEntity = mapper.toEntity(dto);

        if (employeeEntity.getEmails() != null) {
            employeeEntity.getEmails().forEach(email -> email.setEmployee(employeeEntity));
        }

        Employee savedEmployee = employeeRepo.save(employeeEntity);
        return mapper.toDto(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long id, @Valid EmployeeDTO details) {

        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        // 2. Update fields
        existing.setName(details.getName());
        existing.setAge(details.getAge());
        existing.setSalary(details.getSalary());


        return mapper.toDto(employeeRepo.save(existing));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return mapper.toEmployeeDtoList(employeeRepo.findAll());
    }

    @Override
    public EmployeeDTO getById(Long id) {
        return mapper.toDto(employeeRepo.findById(id).orElseThrow());
    }

    @Override
    public List<EmployeeDTO> getByIds(List<Long> ids) {
        return mapper.toEmployeeDtoList(employeeRepo.findByIdIn(ids));
    }

    @Override
    public List<EmployeeDTO> getByNames(List<String> names) {
        return mapper.toEmployeeDtoList(employeeRepo.findByNameIn(names));
    }
}




