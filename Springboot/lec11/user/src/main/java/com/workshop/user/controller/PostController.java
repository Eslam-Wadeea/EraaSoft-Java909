package com.workshop.user.controller;

import com.workshop.user.DTO.PostDto;
import com.workshop.user.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired private PostService postService;

    @PostMapping
    public PostDto createPost(@Valid @RequestBody PostDto dto) {
        return postService.createPost(dto);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // Relationship Endpoints
    @GetMapping("/postsWithUsers")
    public List<PostDto> getPostsWithUsers() {
        return postService.getAllPosts(); // Mapper already includes userId
    }

    @GetMapping("/postWithUsers/{id}")
    public PostDto getPostWithUser(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}
