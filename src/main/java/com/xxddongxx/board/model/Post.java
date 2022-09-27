package com.xxddongxx.board.model;

import com.xxddongxx.board.utils.BaseTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "post")
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    private String title;

    @Column(length = 100000000)
    private String content;

    private boolean isDelete = false;
    private Long viewCount = 0L;

    public Post updateViewCount() {
        this.viewCount += 1L;
        return this;
    }
}
