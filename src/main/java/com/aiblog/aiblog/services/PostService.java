package com.aiblog.aiblog.services;

import com.aiblog.aiblog.models.PostModel;
import com.aiblog.aiblog.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    final PostRepository postRepository;
    public PostService (PostRepository postRepository) { //Dependency Injection
        this.postRepository = postRepository;
    } // Injection point

    public List<PostModel> findAll() { // Return all registers
        return postRepository.findAll();
    }

    public Optional<PostModel> findById(UUID id) {
        return postRepository.findById(id);
    }

    public PostModel save(PostModel postModel) {
        postModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        postModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return postRepository.save(postModel);
    }

    public PostModel update(PostModel postModel) {
        postModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return postRepository.save((postModel));
    }
    public boolean existsById(UUID id) {
        return postRepository.existsById(id);
    }
    @Transactional
    public void delete (UUID id) {
        postRepository.deleteById(id);
    }
}
