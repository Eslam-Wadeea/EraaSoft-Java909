package com.workshop.user.DTO;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
@Getter
@Setter
public class PostDto {
    private Long id;

    @NotNull
    @Size(min = 20, message = "Text must be at least 20 characters")
    private String text;

    private String imagePath;
    private Long userId; // For mapping context

    public void setUser(UserDto userDto) {
        this.userId = userDto.getId();
    }
}

