package com.workshop.employee.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    private Long id;

    @NotBlank(message = "Email type (name) is required")
    private String name;

    @Email(message = "Content must be a valid email pattern")
    @NotBlank(message = "Email content is required")
    private String content;



    public void setEmployee(@Valid EmployeeDTO employee) {
        this.id = employee.getId();
        this.name = employee.getName();
    }
}
