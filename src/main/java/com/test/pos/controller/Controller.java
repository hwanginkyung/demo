package com.test.pos.controller;
import com.test.pos.dto.PostDto;
import com.test.pos.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class Controller {
    private final PostService postService;
    @PostMapping("/post")
    public PostDto.Response createPost(@RequestBody PostDto.Request post){
        return postService.createPost(post);
    }
    @GetMapping("/{postid}")
    public PostDto.Response getPosts(@PathVariable int postid){
        return postService.getPost(postid);
    }
    @GetMapping
    public List<PostDto.ResponseList> getPost(){
        return postService.getPostList();
    }
}
