package com.xxddongxx.board.homecontroller;

import com.xxddongxx.board.dto.PageDto;
import com.xxddongxx.board.dto.PostDto;
import com.xxddongxx.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @Autowired
    PostService postService;

    private int count = 0;

    @GetMapping("/")
    public String Home(Model model, @PageableDefault(page = 0, size = 10, sort = "postNo", direction = Sort.Direction.DESC) Pageable pageable){
        if (count < 1) {
            for (int i=1; i <= 40; i++) {
                if (i <= 20) {
                    String contentText = "<p style=\"text-align: center; \"><span style=\"font-size: 50px;\"><font color=\"#73a5ad\"><b><i>테스트 내용입니다:[%03d]</i></b></font></span></p>";

                    String title = String.format("테스트 데이터입니다:[%03d]", i);
                    String content = String.format(contentText, i);
                    PostDto postDto = new PostDto(title, content);

                    this.postService.createPost(postDto);
                } else {
                    String contentText = "<p style=\"text-align: center; \"><span style=\"font-size: 50px;\"><font color=\"#73a5ad\"><b><i>test 내용입니다:[%03d]</i></b></font></span></p>";

                    String title = String.format("test 데이터입니다:[%03d]", i);
                    String content = String.format(contentText, i);
                    PostDto postDto = new PostDto(title, content);

                    this.postService.createPost(postDto);
                }
            }
            count+=1;
        }
        PageDto pagePosts = this.postService.readPostAll(pageable);
        model.addAttribute("pagePosts", pagePosts);
        return "index";
    }

    @GetMapping("/create")
    public String CreatePost() {
        return "create";
    }

    @GetMapping("/view/post/{postNo}")
    public String readPost(Model model, @PathVariable("postNo") Long postNo) {
        this.postService.selectPost(postNo);
        PostDto targetPost = this.postService.readPost(postNo);
        model.addAttribute("post", targetPost);
        return "detail";
    }

    @GetMapping("/view/update/post/{postNo}")
    public String updatePost(@PathVariable("postNo") Long postNo) {
        return "create";
    }

    @GetMapping("view/search")
    public String searchPost(
            Model model,
            @RequestParam(value = "keyword") String keyword,
            @PageableDefault(page = 0, size = 10, sort = "postNo", direction = Sort.Direction.DESC) Pageable pageable)
    {
        PageDto pagePosts = this.postService.searchPost(keyword, pageable);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pagePosts", pagePosts);
        return "index";
    }

}
