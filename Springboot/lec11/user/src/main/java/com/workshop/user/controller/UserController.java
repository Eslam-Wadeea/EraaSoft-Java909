package com.workshop.user.controller;

import com.workshop.user.DTO.PostDto;
import com.workshop.user.DTO.UserDto;
import com.workshop.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    // 2. Get Specific User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // 3. Get All Users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 4. Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    // 5. Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // --- Relationship Endpoints ---

    // 6. List all posts created by a particular user
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getPostsByUserId(id));
    }

    // 7. Get all users with all their posts
    @GetMapping("/usersWithPost")
    public ResponseEntity<List<UserDto>> getAllUsersWithPosts() {
        return ResponseEntity.ok(userService.getAllUsersWithPosts());
    }

    // 8. Get specific user with all their posts
    @GetMapping("/userWithPost/{id}")
    public ResponseEntity<UserDto> getUserWithPosts(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserWithPosts(id));
    }
}