package com.xxddongxx.board.service;

import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.model.Post;
import com.xxddongxx.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDto createPost(PostDto postDto) {
        String newTitle = postDto.getTitle();
        String newContent = postDto.getContent();

        Post newPost = new Post();
        newPost.setTitle(newTitle);
        newPost.setContent(newContent);

        this.postRepository.save(newPost);

        return new PostDto(newPost);
    }

    public List<PostDto> readPostAll() {
        List<PostDto> postDtoList = new ArrayList<>();
        postRepository.findAll().stream().filter(post -> !post.isDelete()).forEach(post -> postDtoList.add(new PostDto(post)));
        return postDtoList;
    }

    public PostDto readPost(Long postNo) {
        Optional<Post> targetPost = postRepository.findById(postNo).filter(post -> !post.isDelete());

        if (targetPost.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new PostDto(targetPost.get());
    }
}
