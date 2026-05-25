package com.workshop.user.service;

import com.workshop.user.DTO.PostDto;
import com.workshop.user.mapper.PostMapper;
import com.workshop.user.model.Post;
import com.workshop.user.model.User;
import com.workshop.user.repository.PostRepository;
import com.workshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PostMapper postMapper;

    // Handles: postService.createPost(dto)
    public PostDto createPost(PostDto postDto) {
        // Find the user first to establish the relationship
        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + postDto.getUserId()));

        Post post = postMapper.toEntity(postDto, user);
        return postMapper.toDto(postRepository.save(post));
    }

    // Handles: postService.getPostById(id)
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
        return postMapper.toDto(post);
    }

    // Handles: postService.getAllPosts()
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }
}
