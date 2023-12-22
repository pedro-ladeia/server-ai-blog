package com.aiblog.aiblog.controllers;

import com.aiblog.aiblog.models.PostModel;
import com.aiblog.aiblog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/post")
public class PostController {
    final PostService postService;
    public PostController(PostService postService) { // Injection point
        this.postService = postService;
    }
    @GetMapping("")
    public ResponseEntity<List<PostModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAll());
    }
}
