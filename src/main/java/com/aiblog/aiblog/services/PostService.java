package com.aiblog.aiblog.services;

import com.aiblog.aiblog.models.PostModel;
import com.aiblog.aiblog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    final PostRepository postRepository;
    public PostService (PostRepository postRepository) { //Dependency Injection
        this.postRepository = postRepository;
    } // Injection point

    public List<PostModel> findAll() { // Return all registers
        return postRepository.findAll();
    }
}
