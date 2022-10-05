package com.xxddongxx.board.controller;

import com.xxddongxx.board.dto.PageDto;
import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.service.PostService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Api(tags = "1. post", description = "post controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostRestController {
    private final Logger logger = LoggerFactory.getLogger(PostRestController.class);

    @Autowired
    PostService postService;

    @ApiOperation(value = "전체 게시물 조회")
    @GetMapping("/posts")
    public ResponseEntity<PageDto> readPostAll(@PageableDefault(page = 0, size = 10, sort = "postNo", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(this.postService.readPostAll(pageable));
    }

    @ApiOperation(value = "게시물 조회")
    @GetMapping("/post/{postNo}")
    public ResponseEntity<PostDto> readPost(@PathVariable("postNo") Long postNo) {
        return ResponseEntity.ok(this.postService.readPost(postNo));
    }

    @ApiOperation(value = "게시물 생성")
    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(this.postService.createPost(postDto));
    }

    @ApiOperation(value = "게시물 삭제")
    @PutMapping("/post/delete/{postNo}")
    public void deletePost(@PathVariable("postNo") Long postNo) {
        this.postService.deletePost(postNo);
    }

    @ApiOperation(value = "게시물 업데이트")
    @PutMapping("/post/{postNo}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("postNo") Long postNo, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(this.postService.updatePost(postNo, postDto));
    }

    @ApiOperation(value = "게시물 검색")
    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<PageDto> searchPost(
            @PathVariable("keyword") String keyword,
            @PageableDefault(page = 0, size = 10, sort = "postNo", direction = Sort.Direction.DESC) Pageable pageable)
    {
        logger.info("search keyword >>> " + keyword);
        return ResponseEntity.ok(this.postService.searchPost(keyword, pageable));
    }
}
