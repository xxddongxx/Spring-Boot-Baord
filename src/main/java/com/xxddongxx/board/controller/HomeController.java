package com.xxddongxx.board.controller;

import com.xxddongxx.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String Home(){
        return "index";
    }

    @GetMapping("/create")
    public String CreatePost() {
        return "create";
    }

    @GetMapping("/view/post/{postNo}")
    public String readPost(@PathVariable("postNo") Long postNo) {
        this.postService.selectPost(postNo);
        return "detail";
    }

}
