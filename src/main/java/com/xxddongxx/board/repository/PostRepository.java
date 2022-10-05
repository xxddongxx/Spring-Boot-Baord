package com.xxddongxx.board.repository;

import com.xxddongxx.board.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByIsDelete(Boolean isDelete, Pageable pageable);
    Page<Post> findAllByIsDeleteAndTitleContainingIgnoreCaseOrIsDeleteAndContentContainingIgnoreCase(Boolean titleDelete, String title, Boolean contentDelete, String content, Pageable pageable);
}
