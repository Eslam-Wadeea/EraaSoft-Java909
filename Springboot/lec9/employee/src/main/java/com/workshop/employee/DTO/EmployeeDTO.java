package com.workshop.employee.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 16, message = "Age must be greater than 15")
    @Max(value = 39, message = "Age must be less than 40")
    private int age;

    @DecimalMin(value = "5000", message = "Salary must be greater than 5000")
    @DecimalMax(value = "9999", message = "Salary must be less than 10000")
    private double salary;

    private List<EmailDTO> emails;
}
