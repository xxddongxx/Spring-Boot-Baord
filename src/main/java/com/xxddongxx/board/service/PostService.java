package com.xxddongxx.board.service;

import com.xxddongxx.board.dto.PageDto;
import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.model.Post;
import com.xxddongxx.board.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
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

    public PageDto readPostAll(Pageable pageable) {
        Page<Post> pagePosts = this.postRepository.findAllByIsDelete(false, pageable);

        long totalCount = pagePosts.getTotalElements();
        int nowPage = pagePosts.getPageable().getPageNumber() + 1;
        int startPage = Math.max(1, nowPage - 4);
        int endPage = Math.min(pagePosts.getTotalPages(), nowPage + 4);

        PageDto pageDto = new PageDto(nowPage, startPage, endPage, pagePosts);
        logger.info(pageDto.toString());
        return pageDto;
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

    public PageDto searchPost(String keyword, Pageable pageable) {
        Page<Post> pagePosts = this.postRepository.findAllByIsDeleteAndTitleContainingIgnoreCaseOrIsDeleteAndContentContainingIgnoreCase(false, keyword, false, keyword, pageable);

        if (pagePosts.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        long totalCount = pagePosts.getTotalElements();
        int nowPage = pagePosts.getPageable().getPageNumber() + 1;
        int startPage = Math.max(1, nowPage - 4);
        int endPage = Math.min(pagePosts.getTotalPages(), nowPage + 4);

        PageDto pageDto = new PageDto(nowPage, startPage, endPage, pagePosts);
        logger.info(pageDto.toString());

        return pageDto;
    }
}
