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
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<PostModel> allPosts = postService.findAll();
        if(!allPosts.isEmpty()) {
            for (PostModel postModel : allPosts) {
                UUID id = postModel.getIdPost();
                postModel.add(linkTo(methodOn(PostController.class).findById(id)).withSelfRel());
                // Add the endpoint of that register to each register
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(allPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Optional<PostModel> postModelOptional = postService.findById(id);
        if(!postModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication not found");
        postModelOptional.get().add(linkTo(methodOn(PostController.class).findAll()).withRel("Publications"));
        // Add the endpoint of the all publications to the register
        return ResponseEntity.status(HttpStatus.OK).body(postModelOptional.get());
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
