package com.xxddongxx.board.homecontroller;

import com.xxddongxx.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/view/update/post/{postNo}")
    public String updatePost(@PathVariable("postNo") Long postNo) {
        return "create";
    }

    @GetMapping("view/search")
    public String searchPost(@RequestParam(value = "keyword") String keyword) {
        return "index";
    }

}
