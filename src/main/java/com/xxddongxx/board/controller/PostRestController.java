package com.xxddongxx.board.controller;

import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.service.PostService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @GetMapping("/posts")
    public ResponseEntity<Collection<PostDto>> readPostAll() {
        return ResponseEntity.ok(this.postService.readPostAll());
    }

    @ApiOperation(value = "게시물 조회")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @GetMapping("/post/{postNo}")
    public ResponseEntity<PostDto> readPost(@PathVariable("postNo") Long postNo) {
        return ResponseEntity.ok(this.postService.readPost(postNo));
    }

    @ApiOperation(value = "게시물 생성")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(this.postService.createPost(postDto));
    }


    @ApiOperation(value = "게시물 삭제")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @PutMapping("/post/delete/{postNo}")
    public void deletePost(@PathVariable("postNo") Long postNo) {
        this.postService.deletePost(postNo);
    }

    @ApiOperation(value = "게시물 업데이트")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @PutMapping("/post/{postNo}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("postNo") Long postNo, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(this.postService.updatePost(postNo, postDto));
    }

    @ApiOperation(value = "게시물 검색")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<Collection<PostDto>> searchPost(@PathVariable("keyword") String keyword) {
        logger.info("search keyword >>> " + keyword);
        return ResponseEntity.ok(this.postService.searchPost(keyword));
    }
}
