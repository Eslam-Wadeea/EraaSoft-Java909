package com.workshop.service;

import com.workshop.model.Friend;
import com.workshop.model.Post;
import com.workshop.model.User;
import com.workshop.model.UserDetails;
import com.workshop.repository.FriendRepository;
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

    @Autowired
    private FriendRepository friendRepository;

    @Transactional
    public void addNewUserWithData() {

        UserDetails details = new UserDetails();
        details.setAddress("Alexandria");
        details.setPhone("0123456789");
        userDetailsRepository.save(details);

        User user = new User();
        user.setName("Eslam");
        user.setAge(25);
        user.setUserDetails(details);
        userRepository.save(user);


        Post post = new Post();
        post.setHeader("Spring Boot Task");
        post.setContent("Applying Task 2 without cascade");
        post.setUser(user);
        postRepository.save(post);


        Friend friend = new Friend();
        friend.setName("Ahmed");
        friendRepository.save(friend);


        user.getFriends().add(friend);
        userRepository.save(user);
    }
}
