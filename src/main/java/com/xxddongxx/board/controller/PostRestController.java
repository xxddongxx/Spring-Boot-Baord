package com.xxddongxx.board.controller;

import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PostRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Collection<PostDto>> readPostAll() {
        return ResponseEntity.ok(this.postService.readPostAll());
    }

    @GetMapping("/post/{postNo}")
    public ResponseEntity<PostDto> readPost(@PathVariable("postNo") Long postNo) {
        return ResponseEntity.ok(this.postService.readPost(postNo));
    }

    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(this.postService.createPost(postDto));
    }
}
