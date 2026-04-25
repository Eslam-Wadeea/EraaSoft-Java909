package com.workshop.service;

import com.workshop.model.Post;
import com.workshop.model.User;
import com.workshop.model.UserDetails;
import com.workshop.repository.PostRepository;
import com.workshop.repository.UserDetailsRepository;
import com.workshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void addNewUserWithData() {

        UserDetails details = new UserDetails();
        details.setAddress("Alexandria");
        userDetailsRepository.save(details);

        User user = new User();
        user.setName("Eslam");
        user.setUserDetails(details);
        userRepository.save(user);


        Post post = new Post();
        post.setHeader("Spring Task");
        post.setUser(user);
        postRepository.save(post);
    }
}
