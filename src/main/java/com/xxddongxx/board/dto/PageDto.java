package com.xxddongxx.board.dto;

import com.xxddongxx.board.model.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.Collection;

@Getter
@Setter
@ToString
public class PageDto {
    private int nowPage;
    private int startPage;
    private int endPage;
    private Page<Post> postResults;

    public PageDto() {

    }

    public PageDto(int nowPage, int startPage, int endPage, Page<Post> postResults) {
        this.nowPage = nowPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.postResults = postResults;
    }
}
