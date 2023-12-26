package com.aiblog.aiblog.controllers;

import com.aiblog.aiblog.dtos.PostDto;
import com.aiblog.aiblog.models.PostModel;
import com.aiblog.aiblog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<PostModel> save(@RequestBody @Valid PostDto postDto) { //@Valid to do all validations
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postModel));
    }
}
