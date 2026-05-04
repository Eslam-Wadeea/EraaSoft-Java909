package com.workshop.employee.mapper;

import com.workshop.employee.DTO.EmailDTO;
import com.workshop.employee.DTO.EmployeeDTO;
import com.workshop.employee.model.Email;
import com.workshop.employee.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EmployeeDTO toDto(Employee entity);
    Employee toEntity(EmployeeDTO dto);

    EmailDTO toDto(Email entity);
    Email toEntity(EmailDTO dto);

    List<EmployeeDTO> toEmployeeDtoList(List<Employee> entities);
    List<EmailDTO> toEmailDtoList(List<Email> entities);
}
