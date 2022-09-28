package com.xxddongxx.board.service;

import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.model.Post;
import com.xxddongxx.board.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    PostRepository postRepository;

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
        this.postRepository.findAll().stream().filter(post -> !post.isDelete()).forEach(post -> postDtoList.add(new PostDto(post)));
        return postDtoList;
    }

    public Post selectPost(Long postNo) {
        Optional<Post> selectPost = this.postRepository.findById(postNo).filter(post -> !post.isDelete());
        Post post = selectPost.get();
        post.updateViewCount();
        this.postRepository.save(post);
        return post;
    }

    public PostDto readPost(Long postNo) {
        Optional<Post> targetPost = this.postRepository.findById(postNo).filter(post -> !post.isDelete());

        if (targetPost.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new PostDto(targetPost.get());
    }

    @Transactional
    public PostDto updatePost(Long postNo, PostDto postDto) {
        Optional<Post> targetPost = this.postRepository.findById(postNo).filter(post -> !post.isDelete());

        if (targetPost.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Post post = targetPost.get();
        post.update(postDto.getTitle(), postDto.getContent());

        return new PostDto(post);
    }

    public void deletePost(Long postNo) {
        Optional<Post> targetPost = this.postRepository.findById(postNo).filter(post -> !post.isDelete());

        if (targetPost.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Post post = targetPost.get();
        post.setDelete(true);
        this.postRepository.save(post);
    }

    public List<PostDto> searchPost(String keyword) {
        List<PostDto> searchPostList = new ArrayList<>();

        this.postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword).stream().filter(post -> !post.isDelete()).forEach(post -> searchPostList.add(new PostDto(post)));
        if (searchPostList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        logger.info("search Post List >>> " + searchPostList);

        return searchPostList;
    }
}
