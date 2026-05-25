package com.workshop.user.service;

import com.workshop.user.DTO.PostDto;
import com.workshop.user.DTO.UserDto;
import com.workshop.user.mapper.PostMapper;
import com.workshop.user.mapper.UserMapper;
import com.workshop.user.model.User;
import com.workshop.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private UserMapper userMapper;
    @Autowired private PostMapper postMapper;

    // For: GET /users/usersWithPost
    public List<UserDto> getAllUsersWithPosts() {
        return userRepository.findAll().stream()
                .map(this::convertToDtoWithPosts)
                .collect(Collectors.toList());
    }

    // For: GET /users/userWithPost/{id}
    public UserDto getUserWithPosts(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDtoWithPosts(user);
    }

    // Helper to map User and their List<Post> to DTO
    private UserDto convertToDtoWithPosts(User user) {
        UserDto dto = userMapper.toDto(user);
        dto.setPosts(user.getPosts().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }


    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(userDto.getName());
        existingUser.setAge(userDto.getAge());
        // Only update password if provided/necessary
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }

        return userMapper.toDto(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<PostDto> getPostsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return user.getPosts().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        // 1. Map DTO to Entity
        User user = userMapper.toEntity(userDto);

        // 2. Save Entity to Database
        User savedUser = userRepository.save(user);

        // 3. Map Saved Entity back to DTO
        return userMapper.toDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        // Find the user or throw an exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Map the Entity to DTO and return
        return userMapper.toDto(user);
    }


}
