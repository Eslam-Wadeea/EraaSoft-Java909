package com.workshop.user.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {
    private Long id;

    @Size(min = 8, message = "Name must be greater than 7 characters")
    private String name;

    @Min(value = 18, message = "Age must be exactly 18 or older")
    private int age;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have upper, lower, number, and special character"
    )
    private String password;

    private List<PostDto> posts;

}

