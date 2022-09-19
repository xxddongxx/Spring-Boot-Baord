package com.xxddongxx.board.dto;

import com.xxddongxx.board.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private String title;
    private String content;

    public PostDto() {
    }

    public PostDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
