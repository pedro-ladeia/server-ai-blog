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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<PostModel> postModelOptional = postService.findById(id);
        if(!postModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication not found");
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<PostModel> save(@RequestBody @Valid PostDto postDto) { //@Valid to do all validations
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value= "id") UUID id) {
        Optional<PostModel> postModelOptional = postService.findById(id);
        if(!postModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication not found");
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Publication deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PostDto postDto) {
        Optional<PostModel> postModelOptional = postService.findById(id);
        if(!postModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication not found");
        var postModel = postModelOptional.get();
        BeanUtils.copyProperties(postDto, postModel);
        return ResponseEntity.status(HttpStatus.OK).body(postService.update(postModel));
    }
}
