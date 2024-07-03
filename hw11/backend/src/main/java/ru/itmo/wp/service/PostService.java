package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public PostService(PostRepository postRepository, UserRepository userRepository, JwtService jwtService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public void writePost(PostCredentials postCredentials) {
        Post post = new Post();
        User user = jwtService.find(postCredentials.getJwt());
        post.setUser(user);
        post.setTitle(postCredentials.getTitle());
        post.setText(postCredentials.getText());
        postRepository.save(post);
        userRepository.save(user);
    }
}
