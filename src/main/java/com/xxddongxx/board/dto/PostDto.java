package com.xxddongxx.board.dto;

import com.xxddongxx.board.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {

    private String title;
    private String content;
    private Long viewCount;
    private LocalDateTime createdAt;

    public PostDto() {
    }

    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.createdAt = post.getCreatedAt();
    }
}
