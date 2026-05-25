package com.workshop.user.mapper;


import com.workshop.user.DTO.UserDto;
import com.workshop.user.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setPassword(dto.getPassword());
        return user;
    }
}
