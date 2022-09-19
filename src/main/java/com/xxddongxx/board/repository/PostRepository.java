package com.xxddongxx.board.repository;

import com.xxddongxx.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
