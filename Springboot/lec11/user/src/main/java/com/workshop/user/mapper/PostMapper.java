package com.workshop.user.mapper;

import com.workshop.user.DTO.PostDto;
import com.workshop.user.model.Post;
import com.workshop.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDto toDto(Post post) {
        if (post == null) return null;
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setText(post.getText());
        dto.setImagePath(post.getImagePath());

        if (post.getUser() != null) {
            dto.setUserId(post.getUser().getId());
        }
        return dto;
    }

    public Post toEntity(PostDto dto, User user) {
        if (dto == null) return null;
        Post post = new Post();
        post.setText(dto.getText());
        post.setImagePath(dto.getImagePath());
        post.setUser(user);
        return post;
    }
}
